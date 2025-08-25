package com.iokays.common.mongodb;

import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用于标识领域对象在数据库持久层的位置基类
 * <p>
 * <p>
 * 领域对象的业务并不关心数据库主键. 只是领域对象在数据库中的持久化需要主键映射数据的位置。
 */
public class IdentifiedDomainObject implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @MongoId
    private String id;

}
