package com.zbq.dingrobot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DingRobotApplication {

    public static void main(String[] args) {
        SpringApplication.run(DingRobotApplication.class, args);
    }

}
