// Path: Samples/lombok-data-inheritance-issue-sample/src/main/java/com/iokays/sample/lombok/solution/targeted/User.java
package com.iokays.sample.lombok.solution.targeted;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 解决方案3: 避免使用@Data，改用更精确的注解
 * <p>
 * 我们放弃使用@Data，因为它会隐式地尝试生成一个无参构造函数。
 * 相反，我们只使用我们确实需要的注解，如 @Getter, @Setter, @ToString, @EqualsAndHashCode。
 * 这样，Lombok就不会生成任何构造函数，我们可以完全控制构造函数的行为。
 * <p>
 * 优点: 控制力最强，代码意图最清晰。
 * 缺点: 需要手动添加多个注解。
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {
    private String name;

    public User(String createdBy, String name) {
        super(createdBy);
        this.name = name;
    }
}
