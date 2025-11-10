// Path: Samples/lombok-data-inheritance-issue-sample/src/test/java/com/iokays/sample/lombok/solution/force/ForceSolutionTest.java
package com.iokays.sample.lombok.solution.force;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ForceSolutionTest {

    @Test
    void testForceNoArgsConstructor() {
        // @Data 生成的无参构造函数
        User user = new User();
        user.setName("测试用户");

        // 因为调用了无参构造函数，final字段被Lombok强制初始化为null
        assertNull(user.getCreatedBy());
        assertEquals("测试用户", user.getName());

        // 我们自己定义的构造函数依然有效
        User userWithCreator = new User("张三", "李四");
        assertEquals("张三", userWithCreator.getCreatedBy());
        assertEquals("李四", userWithCreator.getName());
    }
}
