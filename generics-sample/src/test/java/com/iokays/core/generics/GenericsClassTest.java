package com.iokays.core.generics;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * 泛型类 测试
 *
 * 1.范型放在类名后面
 * 2. 为什么 Java 不允许类级别的 super 泛型约束
 *    super 约束没有对应的运行时表示，因为所有类型最终都擦除到 Object. (没有特有的边界类型)
 */
public class GenericsClassTest {

    @Test
    void test() {
        final var g1 = new Generics<String>().set("hello");
        Assertions.assertInstanceOf(String.class, g1.get());

        final var g2 = new Generics<Object>()
                .set("hello")
                .set(1L)
                .set(BigDecimal.ZERO);

        Assertions.assertInstanceOf(Object.class, g2.get());
        Assertions.assertInstanceOf(BigDecimal.class, g2.get());

        //只能是 Number 的子类(含 Number)
        final var ug = new UpperBoundedGenerics<Number>().set(1L).set(1.1f);
        Assertions.assertInstanceOf(Number.class, ug.get());
        Assertions.assertInstanceOf(Float.class, ug.get());
        final var ug1 = new UpperBoundedGenerics<Long>().set(1L);
        Assertions.assertInstanceOf(Float.class, ug.get());
    }
}


class Generics<T> {
    private T t;

    Generics<T> set(T t) {
        this.t = t;
        return this;
    }

    T get() {
        return this.t;
    }

}

// 范型上界
class UpperBoundedGenerics<T extends Number> {
    private T t;

    UpperBoundedGenerics<T> set(T t) {
        this.t = t;
        return this;
    }

    T get() {
        return this.t;
    }
}
