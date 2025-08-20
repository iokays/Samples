package com.iokays.sample.jdk16;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * 简化了 instanceof 检查后的类型转换代码，消除冗余变量声明，使代码更简洁安全。
 */
public class PatternMatchingForInstanceofTest {

    @Test
    void test() {
        List.of(true, false).forEach(b -> {
            final Object o = b ? "Hello World" : 100;
            if (o instanceof String s) {
                //直接使用s变量，无需额外声明和强转
                Assertions.assertEquals("Hello World", s);
                return;
            }
            if (o instanceof Integer i) {
                //直接使用i变量, 无需额外声明和强转
                Assertions.assertEquals(100, i);
                return;
            };
            Assertions.fail();
        });

    }

}
