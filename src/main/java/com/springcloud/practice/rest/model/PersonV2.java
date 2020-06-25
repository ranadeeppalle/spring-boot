package com.springcloud.practice.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class PersonV2 {
    private Name name;
}
