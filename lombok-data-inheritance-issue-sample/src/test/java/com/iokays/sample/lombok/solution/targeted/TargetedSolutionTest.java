// Path: Samples/lombok-data-inheritance-issue-sample/src/test/java/com/iokays/sample/lombok/solution/targeted/TargetedSolutionTest.java
package com.iokays.sample.lombok.solution.targeted;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TargetedSolutionTest {

    @Test
    void testTargetedAnnotations() {
        // 这里没有无参构造函数，因为我们没有用@Data，Lombok也不会生成
        // User user = new User(); // 这行代码会编译失败

        // 只能使用我们自己定义的构造函数
        User user = new User("孙七", "周八");
        user.setName("新名字");

        assertEquals("孙七", user.getCreatedBy());
        assertEquals("新名字", user.getName());
        assertTrue(user.toString().contains("createdBy=孙七"));
    }
}
