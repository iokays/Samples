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
 * @param deletedAt    {@link Instant} 事件发生时间
 * @param customerCode {@link CustomerCode} 客户标识
 */
public record CustomerDeleted(EventId eventId, CustomerCode customerCode,
                              LocalDateTime deletedAt
) implements DomainEvent<CustomerDeleted> {

    /**
     * 发布一个领域事件
     *
     * @param customerCode {@link CustomerCode} 客户标识
     * @return {@link CustomerDeleted} 客户已注册的领域事件
     */
    public static CustomerDeleted issue(final CustomerCode customerCode) {
        return new CustomerDeleted(EventId.generate(), customerCode, LocalDateTime.now());
    }

    @Override
    public boolean sameEventAs(CustomerDeleted other) {
        // 通过判断事件标识是否相等来判断事件是否相等
        return Objects.equals(this.eventId, other.eventId);
    }
}
