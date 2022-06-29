package com.zbq.dingrobot.config;

import com.aliyun.dingtalkoauth2_1_0.Client;
import com.aliyun.teaopenapi.models.Config;
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

    @Bean
    public Client createClient() {
        Config config = new Config();
        config.protocol = "https";
        config.regionId = "central";
        try {
            return new Client(config);
        } catch (Exception e) {
            throw new RuntimeException("create dingTalk client fail");
        }
    }

    /*public static Client getClient(String appKey) {
        Client client = createClient();
        GetAccessTokenRequest request = new GetAccessTokenRequest()
                .setAppKey("")
                .setAppSecret("");
        GetAccessTokenResponse accessToken = client.getAccessToken(request);
        GetAccessTokenResponseBody body = accessToken.getBody();
        String accessToken1 = body.accessToken;

        return null;
    }*/


}
