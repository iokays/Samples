// Path: Samples/lombok-data-inheritance-issue-sample/src/main/java/com/iokays/sample/lombok/solution/targeted/BaseEntity.java
package com.iokays.sample.lombok.solution.targeted;

import lombok.ToString;

/**
 * 解决方案3的基类与问题场景中的基类完全相同。
 * 它有一个final字段和唯一的带参构造函数。
 */
@ToString
public abstract class BaseEntity {
    private final String createdBy;

    protected BaseEntity(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }
}
