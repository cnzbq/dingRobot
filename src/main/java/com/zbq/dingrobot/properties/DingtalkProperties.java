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
     * 应用凭证AppKey
     */
    private String appKey;

    /**
     * 应用凭证AppSecret
     */
    private String appSecret;
}
