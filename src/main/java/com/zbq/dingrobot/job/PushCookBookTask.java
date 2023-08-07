package com.zbq.dingrobot.job;

import com.google.gson.Gson;
import com.zbq.dingrobot.entity.webhook.MarkdownMsg;
import com.zbq.dingrobot.handler.CookBookGetHandler;
import com.zbq.dingrobot.handler.DingtalkWebHookHandler;
import com.zbq.dingrobot.handler.HolidayHandler;
import com.zbq.dingrobot.response.CookBookResponse;
import com.zbq.dingrobot.response.DingtalkPushResponse;
import com.zbq.dingrobot.utils.AppRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Dingwq
 * @since 7/13/2023
 */
@Slf4j
@Component
public class PushCookBookTask {

    @Autowired
    private CookBookGetHandler cookBookGetHandler;

    @Autowired
    private HolidayHandler holidayHandler;

    @Autowired
    private DingtalkWebHookHandler dingtalkWebHookHandler;

    private static final String DATE_PATTERN = "yyyy-MM-dd";

    //  @Scheduled(cron = "0 0/15 10,11 * * ? ")
    @Scheduled(cron = "0 0/1 * * * ? ")
    public void push() throws IOException {
        String lastDate = AppRecord.lastDate();
        LocalDate now = LocalDate.now();
        if (StringUtils.hasText(lastDate)) {
            LocalDate last = LocalDate.parse(lastDate, DateTimeFormatter.ofPattern(DATE_PATTERN));
            if (last.isAfter(now) || last.isEqual(now)) {
                log.info("今天已经推送过了");
                return;
            }
        }

        /*if (holidayHandler.isHoliday(now.format(DateTimeFormatter.ofPattern(DATE_PATTERN)))) {
            log.info("今天是节假日，不推送");
            return;
        }*/

        CookBookResponse cookBookResponse = cookBookGetHandler.get();
        CookBookResponse.CookBook cookBook = cookBookResponse.getData();
        String todayTime = cookBook.getTodayTime().get(0);
        LocalDate dayTime = LocalDate.parse(todayTime, DateTimeFormatter.ofPattern(DATE_PATTERN));
        if (!dayTime.isEqual(now)) {
            log.info("菜单不是今天的，不推送");
            return;
        }

        MarkdownMsg markdownMsg = new MarkdownMsg();
        MarkdownMsg.Markdown markdown = new MarkdownMsg.Markdown();
        markdown.setTitle("今日菜单");
        markdown.setText(cookBook.toMarkdown());
        markdownMsg.setMarkdown(markdown);

        DingtalkPushResponse result = dingtalkWebHookHandler.send(markdownMsg);
        if (0 != result.getErrcode()) {
            log.error("推送失败，{}", new Gson().toJson(result));
            return;
        }

        AppRecord.writeLastDate(now.format(DateTimeFormatter.ofPattern(DATE_PATTERN)));
    }
}
