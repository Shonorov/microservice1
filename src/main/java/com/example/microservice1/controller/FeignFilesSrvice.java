package com.example.microservice1.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(name = "files")
public interface FeignFilesSrvice {

    @GetMapping("/prop")
    String getMS2Prop();

}
