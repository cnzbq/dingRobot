package com.zbq.dingrobot.handler;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.open.app.api.callback.OpenDingTalkCallbackListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 接收消息 {@link <a href="https://open.dingtalk.com/document/orgapp/the-application-robot-in-the-enterprise-sends-a-single-chat">...</a>}
 * 通过webhook回复消息 {@link <a href="https://open.dingtalk.com/document/isvapp/enterprise-internal-robots-use-webhook-to-send-group-chat-messages">...</a>}
 * webhook消息支持类型 {@link <a href="https://open.dingtalk.com/document/orgapp/robot-message-types-and-data-format#title-z74-8to-i7e">...</a>}
 *
 * @author Dingwq
 * @since 6/1/2023
 */
@Slf4j
@Component
public class RobotMsgCallbackConsumer implements OpenDingTalkCallbackListener<JSONObject, JSONObject> {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public JSONObject execute(JSONObject request) {
        try {
            JSONObject text = request.getJSONObject("text");
            if (text != null) {
                String msg = text.getString("content").trim();
                String openConversationId = request.getString("conversationId");
            }

            log.info("接收到了消息：{}", request.toJSONString());

        } catch (Exception e) {
            log.error("receive group message by robot error:" + e.getMessage(), e);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.ALL));

        Map<String, Object> body = new HashMap<>();
        body.put("msgtype", "text");
        body.put("text", "{\"content\":\"[暗中观察]\"}");
        body.put("at", "{\"atUserIds\":\"[]\"}");

        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(body, headers);


        ResponseEntity<String> response1 = restTemplate.postForEntity(request.getString("sessionWebhook"), httpEntity, String.class);
        log.info("消息发送结果：{}", JSON.toJSONString(response1));


        return new JSONObject();
    }
}
