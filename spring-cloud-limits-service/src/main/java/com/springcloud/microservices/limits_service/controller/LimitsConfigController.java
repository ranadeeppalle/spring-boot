package com.springcloud.microservices.limits_service.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigController {
    @Value("${limits-service.minimum}")
    private Integer minimum;

    @Value("${limits-service.maximum}")
    private Integer maximum;

    @GetMapping("/limits")
    public LimitConfiguration retrieveLimitsFromCOnfigurations(){
        return LimitConfiguration.builder().minimum(minimum).maximum(maximum).build();
    }
}
