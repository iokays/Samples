// Path: Samples/lombok-data-inheritance-issue-sample/src/main/java/com/iokays/sample/lombok/solution/force/BaseEntity.java
package com.iokays.sample.lombok.solution.force;

import lombok.NoArgsConstructor;

/**
 * 解决方案1: 使用 @NoArgsConstructor(force = true)
 * <p>
 * 这个注解会强制Lombok生成一个无参构造函数，
 * 并将final字段初始化为默认值（例如null, 0, false）。
 * <p>
 * 优点: 快速解决编译问题。
 * 缺点: 可能导致final字段持有无意义的默认值，破坏了字段的“不变性”初衷，需要谨慎使用。
 */
@NoArgsConstructor(force = true)
public abstract class BaseEntity {
    private final String createdBy;

    public BaseEntity(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }
}
