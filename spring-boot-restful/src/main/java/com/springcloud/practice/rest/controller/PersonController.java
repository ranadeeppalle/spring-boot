package com.springcloud.practice.rest.controller;

import com.springcloud.practice.rest.model.Name;
import com.springcloud.practice.rest.model.PersonV1;
import com.springcloud.practice.rest.model.PersonV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
    @GetMapping("v1/person")
    public PersonV1 personV1() {
        return PersonV1.builder().name("Bob Charlie").build();
    }

    @GetMapping("v2/person")
    public PersonV2 personV2() {
        final Name name = Name.builder().firstName("Bob").lastName("Charlie").build();
        return PersonV2.builder().name(name).build();
    }

    @GetMapping(value = "/person/param", params = "version=1")
    public PersonV1 paramV1() {
        return PersonV1.builder().name("Bob Charlie").build();
    }

    @GetMapping(value = "/person/param", params = "version=2")
    public PersonV2 paramV2() {
        final Name name = Name.builder().firstName("Bob").lastName("Charlie").build();
        return PersonV2.builder().name(name).build();
    }

    @GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 personHeaderV1() {
        return PersonV1.builder().name("Bob Charlie").build();
    }

    @GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 personHeaderV2() {
        final Name name = Name.builder().firstName("Bob").lastName("Charlie").build();
        return PersonV2.builder().name(name).build();
    }


    @GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v1+json")
    public PersonV1 personMimeV1() {
        return PersonV1.builder().name("Bob Charlie").build();
    }

    @GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v2+json")
    public PersonV2 personMimeV2() {
        final Name name = Name.builder().firstName("Bob").lastName("Charlie").build();
        return PersonV2.builder().name(name).build();
    }
}
