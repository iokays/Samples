// Path: Samples/lombok-data-inheritance-issue-sample/src/main/java/com/iokays/sample/lombok/solution/jpa/User.java
package com.iokays.sample.lombok.solution.audit;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

    private Long id;

    private String username;

    // Lombok的@Data和@NoArgsConstructor会生成一个无参构造函数，它会调用super()。
    // 因为BaseEntity现在有了一个无参构造函数，所以一切正常。
}
