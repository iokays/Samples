package com.iokays.common.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;

import java.io.Serial;

/**
 * 具有版本号的领域对象
 * <p>
 * 用于领域层与数据库层的数据版本控制
 * <p/>
 */
@MappedSuperclass
public abstract class ConcurrencySafeEntity<T> extends IdentifiedEntity<T> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    @Version
    private int concurrencyVersion;

    protected ConcurrencySafeEntity() {
        super();
    }

    public int concurrencyVersion() {
        return this.concurrencyVersion;
    }

}
