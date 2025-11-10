// Path: Samples/lombok-data-inheritance-issue-sample/src/main/java/com/iokays/sample/lombok/solution/jpa/BaseEntity.java
package com.iokays.sample.lombok.solution.audit;

import java.time.LocalDateTime;

/**
 * 审计场景: 一个典型的包含审计字段的基类。
 * <p>
 * 这里我们使用“显式提供默认构造函数”的方案，这是在实体中最常见和推荐的做法。
 */
public abstract class BaseEntity {

    private final LocalDateTime createdAt;

    /**
     * 为JPA提供者准备的无参构造函数。
     * 同时为final字段赋予一个有意义的默认值。
     */
    protected BaseEntity() {
        this.createdAt = LocalDateTime.now();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
