package com.iokays.common.core.event;

import java.util.Objects;

/**
 * 领域事件接口
 * 领域事件是唯一的，但没有生命周期的东西。标识可以是显式的，例如付款的序列号，也可以从事件的各个方面派生，例如何时发生了什么。
 */
public interface DomainEvent<T extends DomainEvent<?>> extends Event {

    /**
     * @param other 另一个领域事件。
     * @return <code>true</code>如果给定的领域事件和这个事件被认为是相同的事件。
     */
    default boolean sameEventAs(T other) {
        return Objects.nonNull(other)
                && Objects.equals(this.getClass(), other.getClass())
                && Objects.equals(this.eventId(), other.eventId());
    }

}
