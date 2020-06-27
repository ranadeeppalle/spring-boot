package com.springcloud.microservices.currency_conversion_service.service;

import com.springcloud.microservices.currency_conversion_service.model.CurrencyConversionBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name="currency-exchange-service",url="localhost:8000")
//@FeignClient(name="currency-exchange-service")//without API gateway
@FeignClient(name="netflix-zuul-api-gateway-server")
@RibbonClient(name="currency-exchange-service")
public interface CurrencyExchangeServiceProxy {
    //@GetMapping("/currency-exchange/from/{from}/to/{to}") //without API gateway
    @GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}") ////with API gateway
    public CurrencyConversionBean retrieveExchangeValue
            (@PathVariable("from") String from, @PathVariable("to") String to);
}
