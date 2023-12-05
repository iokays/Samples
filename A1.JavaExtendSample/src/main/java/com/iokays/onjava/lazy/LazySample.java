package com.iokays.onjava.lazy;

import com.google.common.base.Suppliers;
import io.vavr.Lazy;

import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 * 懒加载用例
 */
public class LazySample {

    public static void main(String[] args) {
        withJdk();
        withVavr();
        withGuava();
    }

    private static void withJdk() {
        final Supplier<Integer> supplier = () -> {
            System.out.println("jdk, supplier");
            return 1;
        };

        //每次都运行
        IntStream.range(0, 10).forEach(i -> System.out.println(supplier.get()));
    }

    private static void withVavr() {
        //vavr 实现
        final var lazy = Lazy.of(() -> {
            System.out.println("vavr, lazy");
            return 1;
        });

        //只运行一次
        IntStream.range(0, 10).forEach(i -> System.out.println(lazy.get()));
    }

    private static void withGuava() {
        final var supplier = Suppliers.memoize(() -> {
            System.out.println("guava, supplier");
            return 1;
        });

        //只运行一次
        IntStream.range(0, 10).forEach(i -> System.out.println(supplier.get()));
    }


}
