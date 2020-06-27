package com.springcloud.microservices.limits_service.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springcloud.microservices.limits_service.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigController {
    @Autowired
    Configuration configuration;

    @GetMapping("/limits")
    public LimitConfiguration retrieveLimitsFromCOnfigurations(){
        return LimitConfiguration.builder().minimum(configuration.getMinimum()).maximum(configuration.getMaximum()).build();
    }

    @GetMapping("/hystrix")
    @HystrixCommand(fallbackMethod = "fallbackRetrieveConfiguration")
    public LimitConfiguration retrieveConfiguration(){
        throw new RuntimeException("Dummy");
    }

    public LimitConfiguration fallbackRetrieveConfiguration(){
        return LimitConfiguration.builder().minimum(9).maximum(999).build();
    }
}
