package com.springcloud.microservices.currency_conversion_service.controller;

import com.springcloud.microservices.currency_conversion_service.model.CurrencyConversionBean;
import com.springcloud.microservices.currency_conversion_service.service.CurrencyExchangeServiceProxy;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController

public class CurrencyConversionController {
    @Autowired
    CurrencyExchangeServiceProxy proxy;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to,
                                                  @PathVariable BigDecimal quantity) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        final RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<CurrencyConversionBean> responseEntity = restTemplate.getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class,
                uriVariables);
        final CurrencyConversionBean response = responseEntity.getBody();
        return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
                quantity.multiply(response.getConversionMultiple()), response.getPort());
    }

    @GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from, @PathVariable String to,
                                                  @PathVariable BigDecimal quantity) {
        final CurrencyConversionBean response = proxy.retrieveExchangeValue(from, to);
        logger.info("response -> {} ",response);
        return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
                quantity.multiply(response.getConversionMultiple()), response.getPort());
    }
}
