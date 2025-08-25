package com.iokays.common.mongodb;

import com.iokays.common.core.event.DomainEvent;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 具有唯一标识的领域事件
 * <p>
 * 该标识非业务标识，是数据库持久化的唯一标识
 */
public abstract class IdentifiedDomainEvent<T extends IdentifiedDomainEvent<?>> extends IdentifiedDomainObject implements DomainEvent<T> {

    @Serial
    private static final long serialVersionUID = 1L;

    @CreatedDate
    private LocalDateTime createdDate;


    /**
     * 受保护的构造函数，防止直接实例化，用于Hibernate等ORM框架
     */
    protected IdentifiedDomainEvent() {
        super();
    }
}
