// Path: Samples/lombok-data-inheritance-issue-sample/src/main/java/com/iokays/sample/lombok/solution/explicit/BaseEntity.java
package com.iokays.sample.lombok.solution.explicit;

/**
 * 解决方案2: 提供一个显式的无参构造函数
 * <p>
 * 这是更安全、更推荐的做法。我们自己定义一个无参构造函数，
 * 并为final字段赋予一个有意义的、安全的默认值。
 * <p>
 * 优点: 保证了对象在任何情况下都处于一个有效的状态。
 * 缺点: 需要为基类手动添加构造函数。
 */
public abstract class BaseEntity {
    private final String createdBy;

    /**
     * 显式定义的无参构造函数，为final字段设置一个有意义的默认值。
     */
    protected BaseEntity() {
        this.createdBy = "system"; // 赋予一个有意义的默认值
    }

    protected BaseEntity(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }
}
