package com.springcloud.practice.rest;

import ch.qos.logback.core.util.ContentTypeUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig {
    public static final Contact DEFAULT_CONTACT = new Contact("Ranadeep Palle", "", "ranadeep.palle@gmail.com");
    private static final Set<String> DEFAULT_PRODUCES_CONSUMES = new HashSet<>(Arrays.asList("application/json","application/xml"));

    ApiInfo DEFAULT_API = new ApiInfoBuilder().title("Spring Cloud Demo App").contact(DEFAULT_CONTACT).build();
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(DEFAULT_API).produces(DEFAULT_PRODUCES_CONSUMES).consumes(DEFAULT_PRODUCES_CONSUMES);
    }
}
