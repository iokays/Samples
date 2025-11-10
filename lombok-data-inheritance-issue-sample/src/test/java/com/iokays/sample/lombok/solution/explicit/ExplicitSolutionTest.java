// Path: Samples/lombok-data-inheritance-issue-sample/src/test/java/com/iokays/sample/lombok/solution/explicit/ExplicitSolutionTest.java
package com.iokays.sample.lombok.solution.explicit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExplicitSolutionTest {

    @Test
    void testExplicitDefaultConstructor() {
        // @Data 生成的无参构造函数调用了我们显式定义的 super()
        User user = new User();
        user.setName("测试用户");

        // final字段被初始化为我们在基类中设置的默认值 "system"
        assertEquals("system", user.getCreatedBy());
        assertEquals("测试用户", user.getName());

        // 我们自己定义的构造函数依然有效
        User userWithCreator = new User("王五", "赵六");
        assertEquals("王五", userWithCreator.getCreatedBy());
        assertEquals("赵六", userWithCreator.getName());
    }
}
