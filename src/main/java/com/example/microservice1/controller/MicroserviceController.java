package com.example.microservice1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MicroserviceController {

    @Autowired
    FeignFilesSrvice filesSrvice;

    @Value("${test.value}")
    private String testValue;// = "microservice1";

    @GetMapping("/prop")
    public String getProperty() {
        return testValue;
    }

    @GetMapping("/prop2")
    public String getPropertyFromMS2() {
        return "Value from microservice2: " + filesSrvice.getMS2Prop();
    }
}
