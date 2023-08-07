package com.zbq.dingrobot.handler;

import com.google.gson.Gson;
import com.zbq.dingrobot.properties.CookBookProperties;
import com.zbq.dingrobot.response.CookBookResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

/**
 * @author Dingwq
 * @since 7/13/2023
 */
@Slf4j
@Component
@EnableConfigurationProperties({CookBookProperties.class})
public class CookBookGetHandler {

    @Autowired
    private CookBookProperties cookBookProperties;

    @Autowired
    private RestTemplate restTemplate;

    public CookBookResponse get() {
        String cookUrl = cookBookProperties.getUrl();
        ResponseEntity<String> result = restTemplate.getForEntity(cookUrl, String.class);
        if (result.getStatusCode() != HttpStatus.OK) {
            log.error("获取菜谱失败：{}", result);
            return null;
        }

        CookBookResponse cookBookResponse = new Gson().fromJson(result.getBody(), CookBookResponse.class);
        if (Objects.isNull(cookBookResponse) || cookBookResponse.getCode() != 1) {
            log.error("获取菜谱失败：{}", cookBookResponse);
            return null;
        }

        return cookBookResponse;
    }
}
