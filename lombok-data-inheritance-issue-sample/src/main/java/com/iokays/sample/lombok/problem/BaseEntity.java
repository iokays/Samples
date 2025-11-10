// Path: Samples/lombok-data-inheritance-issue-sample/src/main/java/com/iokays/sample/lombok/problem/BaseEntity.java
package com.iokays.sample.lombok.problem;

/**
 * 抽象基类，包含一个final字段但没有无参构造函数。
 */
public abstract class BaseEntity {
    private final String createdBy;

    /**
     * 唯一的构造函数，要求子类必须提供 createdBy 的值。
     * @param createdBy 创建者
     */
    protected BaseEntity(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }
}
