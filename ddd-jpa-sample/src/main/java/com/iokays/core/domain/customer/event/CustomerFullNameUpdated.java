package com.iokays.core.domain.customer.event;

import com.iokays.common.core.event.DomainEvent;
import com.iokays.common.core.event.EventId;
import com.iokays.core.domain.customer.CustomerCode;
import com.iokays.core.domain.customer.FullName;

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
public record CustomerFullNameUpdated(EventId eventId, CustomerCode customerCode,
                                      FullName fullName,
                                      LocalDateTime registeredAt) implements DomainEvent<CustomerFullNameUpdated> {

    /**
     * 发布一个领域事件
     *
     * @param customerCode {@link CustomerCode} 客户标识
     * @return {@link CustomerFullNameUpdated} 客户已注册的领域事件
     */
    public static CustomerFullNameUpdated issue(final CustomerCode customerCode, FullName fullName, final LocalDateTime registeredAt) {
        return new CustomerFullNameUpdated(EventId.generate(), customerCode, fullName, registeredAt);
    }

    @Override
    public boolean sameEventAs(CustomerFullNameUpdated other) {
        // 通过判断事件标识是否相等来判断事件是否相等
        return Objects.equals(this.eventId, other.eventId);
    }
}
