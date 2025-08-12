package com.iokays.common.jpa;


import com.iokays.common.core.domain.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 领域对象 实体
 */

@DynamicInsert
@DynamicUpdate
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class IdentifiedEntity<T> extends IdentifiedDomainObject implements Entity<T> {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 创建时间，由SpringDataJpa自动生成。
     */
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    /**
     * 修改时间，由SpringDataJpa自动生成。
     */
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime lastModifiedDate;

    /**
     * 受保护的空构造方法，用于Hibernate 从数据库中加载对象时(实例化)使用, 业务代码不应该使用
     */
    protected IdentifiedEntity() {
        super();
    }

    protected LocalDateTime getCreatedDate() {
        return this.createdDate;
    }

    protected LocalDateTime getLastModifiedDate() {
        return this.lastModifiedDate;
    }

}
