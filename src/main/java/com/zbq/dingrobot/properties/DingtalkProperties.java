package com.zbq.dingrobot.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zbq
 * @since 6/29/2022
 */
@Data
@ConfigurationProperties("dingtalk")
public class DingtalkProperties {
    /**
     * 应用appkey
     */
    private String clientId;

    /**
     * 应用appSecret
     */
    private String clientSecret;

    /**
     * 消息回调地址
     * <p>
     *
     * @link {<a href="https://open.dingtalk.com/document/orgapp/the-application-robot-in-the-enterprise-sends-a-single-chat">...</a>}
     */
    private String msgCallbackTopic = "/v1.0/im/bot/messages/get";

    /**
     * 钉钉机器人webhook地址
     */
    private String webHook;

    /**
     * 钉钉机器人加签密钥
     */
    private String secret;
}
