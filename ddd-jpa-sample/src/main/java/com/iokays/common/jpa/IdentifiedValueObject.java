package com.iokays.common.jpa;


import com.iokays.common.core.domain.ValueObject;
import jakarta.persistence.Column;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 具有唯一标识的值对象
 * <p>
 * 该标识非业务标识，是数据库持久化的唯一标识
 */
public abstract class IdentifiedValueObject<T> extends IdentifiedDomainObject implements ValueObject<T> {

    @Serial
    private static final long serialVersionUID = 1L;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    /**
     * 受保护的构造函数，防止直接实例化，用于Hibernate等ORM框架
     */
    protected IdentifiedValueObject() {
        super();
    }
}
