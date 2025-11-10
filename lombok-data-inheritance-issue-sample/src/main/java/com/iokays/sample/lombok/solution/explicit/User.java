// Path: Samples/lombok-data-inheritance-issue-sample/src/main/java/com/iokays/sample/lombok/solution/explicit/User.java
package com.iokays.sample.lombok.solution.explicit;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {
    private String name;

    public User(String createdBy, String name) {
        super(createdBy);
        this.name = name;
    }
}
