package com.springcloud.microservices.limits_service.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LimitConfiguration {
    private int minimum;
    private int maximum;
}
