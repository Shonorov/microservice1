package com.example.microservice1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class Scheduler {

    @Value("${test.value}")
    private String testValue;

    @Scheduled(fixedRate = 2000)
    public void testConfig() {
        System.out.println(testValue);
    }
}
