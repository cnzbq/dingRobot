package com.zbq.dingrobot.handler;

import com.google.gson.Gson;
import com.zbq.dingrobot.properties.HolidayProperties;
import com.zbq.dingrobot.response.HolidayResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author zbq
 * @since 7/14/2023
 */
@Slf4j
@Component
@EnableConfigurationProperties(HolidayProperties.class)
public class HolidayHandler {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HolidayProperties holidayProperties;

    /**
     * @param date yyyy-MM-dd
     * @return true:是节假日
     */
    public boolean isHoliday(String date) {
        //https://date.appworlds.cn/work?date=2021-07-14
        ResponseEntity<String> result = restTemplate.getForEntity(String.format(holidayProperties.getUrl(), date), String.class);
        if (result.getStatusCode() != HttpStatus.OK) {
            log.error("获取节假日失败：{}", result);
            throw new RuntimeException("获取节假日失败");
        }

        HolidayResponse holidayResponse = new Gson().fromJson(result.getBody(), HolidayResponse.class);
        if (holidayResponse.getCode() != 200) {
            log.error("获取节假日失败：{}", holidayResponse);
            throw new RuntimeException("获取节假日失败");
        }

        return holidayResponse.getData().getWork();
    }
}
