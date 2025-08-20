package com.iokays.core.generics;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 泛型方法 测试
 * <p>
 * 泛型方法的定义是在方法返回类型前添加类型参数声明：
 *
 */
public class GenericsMethodTest {

    @Test
    void test() {
        final var a = max(1, 2);
        Assertions.assertInstanceOf(Integer.class, a);
        Assertions.assertInstanceOf(Comparable.class, a);

        Assertions.assertTrue(isGreater(300, 200));
    }

    // 限制类型参数必须实现Comparable接口
    public static <T extends Comparable<T>> T max(T a, T b) {
        return a.compareTo(b) > 0 ? a : b;
    }

    // 多重边界
    public static <T extends Number & Comparable<T>> boolean isGreater(T a, T b) {
        return a.compareTo(b) > 0;
    }


}


