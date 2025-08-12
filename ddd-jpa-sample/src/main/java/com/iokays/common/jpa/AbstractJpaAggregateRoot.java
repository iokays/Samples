package com.iokays.common.jpa;

import com.iokays.common.core.domain.AggregateRoot;
import com.iokays.common.core.event.DomainEvent;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 聚合根
 * 该聚合根实现了Spring Data的{@link DomainEvents}和{@link AfterDomainEventPublication}注解，
 * 需要用户显式的调用 save, delete 方法来触发事件发送
 * 复用了AbstractAggregateRoot 的代码
 * <p>
 * The methods are called every time one of the following a Spring Data repository methods are called:
 * <p>
 * save(…), saveAll(…)
 * delete(…), deleteAll(…), deleteAllInBatch(…), deleteInBatch(…)
 *
 * @param <A>
 * @see org.springframework.data.domain.AbstractAggregateRoot
 */
@MappedSuperclass
public abstract class AbstractJpaAggregateRoot<A extends AbstractJpaAggregateRoot<A>> extends ConcurrencySafeEntity<A> implements AggregateRoot<A> {

    @Transient
    private final transient List<DomainEvent> domainEvents = new ArrayList<>();

    /**
     * Registers the given event object for publication on a call to a Spring Data repository's save or delete methods.
     *
     * @param event must not be {@literal null}.
     * @return the event that has been added.
     * @see #andEvent(DomainEvent)
     */
    protected DomainEvent registerEvent(DomainEvent event) {

        Assert.notNull(event, "Domain event must not be null");

        this.domainEvents.add(event);
        return event;
    }

    /**
     * Clears all domain events currently held. Usually invoked by the infrastructure in place in Spring Data
     * repositories.
     */
    @AfterDomainEventPublication
    protected void clearDomainEvents() {
        this.domainEvents.clear();
    }

    /**
     * All domain events currently captured by the aggregate.
     */
    @DomainEvents
    protected Collection<DomainEvent> domainEvents() {
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
    protected final A andEvent(DomainEvent event) {

        registerEvent(event);

        return (A) this;
    }
}
