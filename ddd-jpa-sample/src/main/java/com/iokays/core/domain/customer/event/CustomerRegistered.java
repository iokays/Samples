package com.iokays.core.domain.customer.event;

import com.iokays.common.core.event.DomainEvent;
import com.iokays.common.core.event.EventId;
import com.iokays.core.domain.customer.CustomerCode;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 客户已注册的领域事件
 *
 * @param eventId      {@link EventId} 事件标识
 * @param registeredAt {@link Instant} 事件发生时间
 * @param customerCode {@link CustomerCode} 客户标识
 */
public record CustomerRegistered(EventId eventId, CustomerCode customerCode,
                                 LocalDateTime registeredAt) implements DomainEvent<CustomerRegistered> {

    /**
     * 发布一个领域事件
     *
     * @param customerCode {@link CustomerCode} 客户标识
     * @return {@link CustomerRegistered} 客户已注册的领域事件
     */
    public static CustomerRegistered issue(final CustomerCode customerCode, final LocalDateTime registeredAt) {
        return new CustomerRegistered(EventId.generate(), customerCode, registeredAt);
    }

    @Override
    public boolean sameEventAs(CustomerRegistered other) {
        // 通过判断事件标识是否相等来判断事件是否相等
        return Objects.equals(this.eventId, other.eventId);
    }
}
