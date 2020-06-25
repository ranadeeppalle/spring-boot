package com.springcloud.practice.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Name {
    private String firstName;
    private String lastName;
}
