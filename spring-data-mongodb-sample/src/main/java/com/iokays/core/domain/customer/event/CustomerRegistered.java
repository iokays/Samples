package com.iokays.core.domain.customer.event;

import com.iokays.common.core.event.DomainEvent;
import com.iokays.common.core.event.EventId;
import com.iokays.core.domain.customer.CustomerCode;

import java.time.Instant;
import java.time.LocalDateTime;

/**
 * 客户已注册的领域事件
 *
 * @param eventId      {@link EventId} 事件标识
 * @param registeredAt {@link Instant} 事件发生时间
 * @param customerId   {@link CustomerCode} 客户标识
 */
public record CustomerRegistered(EventId eventId, CustomerCode customerId,
                                 LocalDateTime registeredAt) implements DomainEvent<CustomerRegistered> {

    /**
     * 发布一个领域事件
     *
     * @param customerId {@link CustomerCode} 客户标识
     * @return {@link CustomerRegistered} 客户已注册的领域事件
     */
    public static CustomerRegistered issue(final CustomerCode customerId, final LocalDateTime registeredAt) {
        return new CustomerRegistered(EventId.generate(), customerId, registeredAt);
    }
}
