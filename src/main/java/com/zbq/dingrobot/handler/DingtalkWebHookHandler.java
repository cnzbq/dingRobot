package com.zbq.dingrobot.handler;

import com.google.gson.Gson;
import com.zbq.dingrobot.entity.webhook.MsgBase;
import com.zbq.dingrobot.properties.DingtalkProperties;
import com.zbq.dingrobot.response.DingtalkPushResponse;
import com.zbq.dingrobot.utils.SignUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author zbq
 * @since 7/14/2023
 */
@Slf4j
@Component
@EnableConfigurationProperties({DingtalkProperties.class})
public class DingtalkWebHookHandler {

    @Autowired
    private DingtalkProperties dingtalkProperties;

    @Autowired
    private RestTemplate restTemplate;

    private final static String PARAM = "&timestamp=%s&sign=%s";

    public <T extends MsgBase> DingtalkPushResponse send(T msg) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.ALL));
        HttpEntity<String> httpEntity = new HttpEntity<>(new Gson().toJson(msg), headers);
        String time = String.valueOf(System.currentTimeMillis());
        String params = String.format(PARAM, time, SignUtil.sign(time, dingtalkProperties.getSecret()));
        ResponseEntity<String> response = restTemplate.postForEntity(dingtalkProperties.getWebHook() + params, httpEntity, String.class);
        log.info("推送结果：{}", new Gson().toJson(response));

        return new Gson().fromJson(response.getBody(), DingtalkPushResponse.class);
    }

}
