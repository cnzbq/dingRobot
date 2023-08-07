package com.zbq.dingrobot.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author zbq
 * @since 6/29/2022
 */
@Data
@ConfigurationProperties("cookbook")
public class CookBookProperties {
    /**
     * 接口地址
     */
    private String url;
}
