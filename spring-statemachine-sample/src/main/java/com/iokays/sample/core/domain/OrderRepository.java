package com.iokays.sample.core.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
public class OrderRepository {

    private final Map<String, Order> orders = new HashMap<>();

    public void save(Order order) {
        log.info("code: {}, entity: {}", order.getOrderCode(), order);
        orders.put(order.getOrderCode(), order);
    }

    public Optional<Order> findByOrderCode(String orderCode) {
        return Optional.ofNullable(orders.get(orderCode));
    }
}