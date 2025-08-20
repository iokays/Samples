package com.iokays.sample.jdk15;

import lombok.Builder;
import org.apache.commons.lang3.ClassUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *  密封类: 使用 sealed 关键字声明，并通过 permits 指定允许的子类
 *
 *  子类必须是以下之一
 * 1. final：无法再被继承。
 * 2. sealed：自身也是密封类，可进一步限制子类。
 * 3. non-sealed：开放继承（解除密封限制）。
 */
public class SealedTest {

    @Test
    void test() {
        final var shape = new Shape();
        final var circle = Circle.builder().radius(10).build();
        final var square = new Square();
        final var rectangle = new Rectangle();
        final var transparentRectangle = new TransparentRectangle();

        Assertions.assertTrue(shape.getClass().isSealed());
        Assertions.assertFalse(transparentRectangle.getClass().isSealed());

        Assertions.assertTrue(ClassUtils.isAssignable(square.getClass(), shape.getClass()));
        Assertions.assertTrue(ClassUtils.isAssignable(circle.getClass(), shape.getClass()));
        Assertions.assertTrue(ClassUtils.isAssignable(rectangle.getClass(), shape.getClass()));
        Assertions.assertTrue(ClassUtils.isAssignable(transparentRectangle.getClass(), shape.getClass()));
    }


}

// 密封类 + 允许的子类列表
sealed class Shape permits Circle, Square, Rectangle {
    // 类定义
}

// 1. final 子类
@Builder
final class Circle extends Shape {
    private final double radius;
}

// 2. sealed 子类（可继续限制）
sealed class Rectangle extends Shape permits TransparentRectangle {
}

// 3. non-sealed 子类（开放继承）
non-sealed class Square extends Shape {
}

// 3. non-sealed 子类（开放继承）
non-sealed class TransparentRectangle extends Rectangle {
}


