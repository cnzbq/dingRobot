package com.zbq.dingrobot.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zbq
 * @since 6/29/2022
 */
@Data
@ConfigurationProperties("holiday")
public class HolidayProperties {
    /**
     * 接口地址
     */
    private String url;
}
