// Path: Samples/lombok-data-inheritance-issue-sample/src/test/java/com/iokays/sample/lombok/solution/jpa/JpaSolutionTest.java
package com.iokays.sample.lombok.solution.audit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuditSolutionTest {

    @Test
    void whenUserIsCreated_thenCreatedAtIsNotNull() {
        User user = new User();
        user.setUsername("林冲");
        user.setId(1L);

        assertNotNull(user.getId());
        assertEquals("林冲", user.getUsername());
        // 验证由BaseEntity的默认构造函数设置的createdAt字段不为空
        assertNotNull(user.getCreatedAt());

        System.out.printf("用户 '%s' 创建成功，创建时间为：%s%n", user.getUsername(), user.getCreatedAt());
    }
}
