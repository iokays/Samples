package com.iokays.common.jpa;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用于标识领域对象在数据库持久层的位置基类
 * <p>
 * <p>
 * 领域对象的业务并不关心数据库主键. 只是领域对象在数据库中的持久化需要主键映射数据的位置。
 */
@MappedSuperclass
@Access(AccessType.FIELD) //使用字段注入，防止贫血
public class IdentifiedDomainObject implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 受保护的空构造方法，用于Hibernate 从数据库中加载对象时(实例化)使用, 业务代码不应该使用
     */
    protected IdentifiedDomainObject() {
        super();
    }

}
