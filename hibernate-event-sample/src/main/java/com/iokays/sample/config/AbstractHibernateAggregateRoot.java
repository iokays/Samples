package com.iokays.sample.config;

import com.iokays.sample.core.common.DomainEvent;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import org.springframework.data.annotation.Transient;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 聚合根
 * 该聚合根 使用 Hibernate 作为持久化引擎
 */
@MappedSuperclass
public abstract class AbstractHibernateAggregateRoot<A extends AbstractHibernateAggregateRoot<A>> {

    @Transient
    private final transient List<DomainEvent<?>> domainEvents = new ArrayList<>();

    @PostPersist //如果给@Id手动赋值, 则不会触发该事件
    @PostUpdate
    @PostRemove
    protected void publishEventsAfterLifecycleCallback() {
        for (Object event : domainEvents) {
            HibernateDomainEventPublisher.publish(event);
        }
        clearDomainEvents();
    }

    protected DomainEvent<?> registerEvent(DomainEvent<?> event) {

        Assert.notNull(event, "Domain event must not be null");

        this.domainEvents.add(event);
        return event;
    }

    protected void clearDomainEvents() {
        this.domainEvents.clear();
    }

    /**
     * All domain events currently captured by the aggregate.
     */
    protected Collection<DomainEvent<?>> domainEvents() {
        return Collections.unmodifiableList(domainEvents);
    }

    /**
     * Adds all events contained in the given aggregate to the current one.
     *
     * @param aggregate must not be {@literal null}.
     * @return the aggregate
     */
    @SuppressWarnings("unchecked")
    protected final A andEventsFrom(A aggregate) {

        Assert.notNull(aggregate, "Aggregate must not be null");

        this.domainEvents.addAll(aggregate.domainEvents());

        return (A) this;
    }

    /**
     * Adds the given event to the aggregate for later publication
     * when calling a Spring Data repository's save or delete method.
     * Does the same as {@link #registerEvent(DomainEvent)} but returns the aggregate instead of the event.
     *
     * @param event must not be {@literal null}.
     * @return the aggregate
     * @see #registerEvent(DomainEvent)
     */
    @SuppressWarnings("unchecked")
    protected final A andEvent(DomainEvent<?> event) {

        registerEvent(event);

        return (A) this;
    }
}
