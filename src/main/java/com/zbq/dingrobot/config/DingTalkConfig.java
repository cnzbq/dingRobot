package com.zbq.dingrobot.config;


import com.dingtalk.open.app.api.OpenDingTalkClient;
import com.dingtalk.open.app.api.OpenDingTalkStreamClientBuilder;
import com.dingtalk.open.app.api.security.AuthClientCredential;
import com.zbq.dingrobot.handler.RobotMsgCallbackConsumer;
import com.zbq.dingrobot.properties.DingtalkProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zbq
 * @since 6/28/2022
 */
@Configuration
@EnableConfigurationProperties(DingtalkProperties.class)
public class DingTalkConfig {

    @Bean(initMethod = "start", destroyMethod = "stop")
    public OpenDingTalkClient createClient(RobotMsgCallbackConsumer consumer, DingtalkProperties properties) {
        return OpenDingTalkStreamClientBuilder
                .custom()
                .credential(new AuthClientCredential(properties.getClientId(), properties.getClientSecret()))
                .registerCallbackListener("/v1.0/im/bot/messages/get", consumer)
                .build();
    }


}
