package com.iokays.sample.jdk11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;

public class VarLambdaTest {

    /**
     * var 支持Lambda参数类型推导
     * 但: 不允许混合使用：同一 Lambda 中不能同时用 var 和显式类型。 (var a, Integer b)
     */
    @Test
    void testVarLambda() {
        final BiFunction<Integer, Integer, String> addFunc = (var a, var b) -> String.valueOf(a + b);
        Assertions.assertEquals("3", addFunc.apply(1, 2));

        //其实这里可以直接简写成 (a, b) -> a + b，推导出两个参数都是int，然后相加。
        final BiFunction<Integer, Integer, String> addFunc2 = (a, b) -> String.valueOf(a + b);
        Assertions.assertEquals("3", addFunc2.apply(1, 2));
    }

}
