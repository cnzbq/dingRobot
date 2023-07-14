package com.zbq.dingrobot.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zbq.dingrobot.entity.webhook.MsgBase;
import com.zbq.dingrobot.properties.DingtalkProperties;
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
 * @author Dingwq
 * @since 7/14/2023
 */
@Slf4j
@Component
@EnableConfigurationProperties({DingtalkProperties.class})
public class DingtalkWebHookHandler {

    @Autowired
    private DingtalkProperties dingtalkProperties;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RestTemplate restTemplate;

    private final static String PARAM = "&timestamp=%s&sign=%s";

    public <T extends MsgBase> String send(T msg) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.ALL));
        HttpEntity<String> httpEntity = new HttpEntity<>(objectMapper.writeValueAsString(msg), headers);
        String time = String.valueOf(System.currentTimeMillis());
        String params = String.format(PARAM, time, SignUtil.sign(time, dingtalkProperties.getSecret()));
        ResponseEntity<String> response = restTemplate.postForEntity(dingtalkProperties.getWebHook() + params, httpEntity, String.class);
        log.info("推送结果：{}", objectMapper.writeValueAsString(response));

        return response.getBody();
    }

}
