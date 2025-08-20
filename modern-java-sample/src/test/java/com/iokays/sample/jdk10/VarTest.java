package com.iokays.sample.jdk10;

import org.apache.commons.lang3.ClassUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

public class VarTest {

    /**
     * var: Java 10 新特性，可以自动推导类型。
     * 适用于任何局部变量和方法参数等，无法用于字段、构造器和全局变量。<br/>
     * var类型推导必须初始化（无法再后面赋值等），也可以赋值null，但是意义不大。
     * <p>
     * 适用场景: 适用于泛型等场合（泛型参数可以推导出类型），减少代码量。
     */
    @Test
    void testVar() {
        final var result = List.of(1L, 2L, 3L, 4L, 5L);
        Assertions.assertTrue(ClassUtils.isAssignable(result.getClass(), Collection.class));
        Assertions.assertTrue(ClassUtils.isAssignable(result.getClass(), List.class));
    }


}
