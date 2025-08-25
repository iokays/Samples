package com.iokays.common.mongodb;

import org.springframework.data.annotation.Version;

import java.io.Serial;

/**
 * 具有版本号的领域对象
 * <p>
 * 用于领域层与数据库层的数据版本控制
 * <p/>
 */
public abstract class ConcurrencySafeEntity<T> extends IdentifiedEntity<T> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Version
    private int concurrencyVersion;

    protected ConcurrencySafeEntity() {
        super();
    }

    public int concurrencyVersion() {
        return this.concurrencyVersion;
    }

}
