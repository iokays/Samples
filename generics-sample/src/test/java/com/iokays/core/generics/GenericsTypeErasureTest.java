package com.iokays.core.generics;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * 范型类型擦除
 * 1. 移除所有类型参数：将泛型类型中的类型参数替换为它们的边界（通常是 Object）
 * 2. 插入必要的类型转换：保证类型安全
 * 3. 生成桥接方法：保持多态性
 */
public class GenericsTypeErasureTest {

    @Test
    void test() {
        // 编译前
        {
            List<String> list = new ArrayList<>();
            list.add("Hello");
            String s = list.get(0);
            Assertions.assertEquals("Hello", s);
        }


        // 编译后（概念上）
        {
            List list = new ArrayList();
            list.add("Hello");
            String s = (String) list.get(0);  // 编译器插入的类型转换
            Assertions.assertEquals("Hello", s);
        }
    }

    /**
     * 不能使用 instanceof 检查泛型类型
     */
    @Test
    void testInstanceof() {
        final List<String> list = new ArrayList<>();
        Assertions.assertEquals(true, list instanceof List);
        Assertions.assertEquals(true, list instanceof List<?>);
        Assertions.assertEquals(true, list instanceof List<String>); //不会编译报错.


    }

}
