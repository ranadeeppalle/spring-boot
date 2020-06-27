package com.springcloud.microservices.currency_exchange_service.controller;

import com.springcloud.microservices.currency_exchange_service.model.ExchangeValue;
import com.springcloud.microservices.currency_exchange_service.repo.ExchangeValueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    Environment environment;

    @Autowired
    ExchangeValueRepository repository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable String from,@PathVariable String to){
        int port = Integer.valueOf(environment.getProperty("local.server.port"));
        final ExchangeValue exchangeValue = repository.findByFromAndTo(from,to);
        exchangeValue.setPort(port);
        logger.info("{}",exchangeValue);
        return exchangeValue;
    }
}
