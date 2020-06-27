package com.springcloud.microservices.currency_exchange_service.repo;

import com.springcloud.microservices.currency_exchange_service.model.ExchangeValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {
    ExchangeValue findByFromAndTo(String from, String to);
}
