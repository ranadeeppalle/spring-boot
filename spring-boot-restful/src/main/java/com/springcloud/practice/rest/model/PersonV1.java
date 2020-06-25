package com.springcloud.practice.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class PersonV1 {
    private String name;
}
