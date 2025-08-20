package com.iokays.sample.jdk14;

import lombok.Builder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RecordTest {

    @Test
    void testRecord() {
        //建议不要直接使用构造方法来新建对象, 而是使用 lombok @Builder 注解。
        final var p = Point.builder().x(3).y(4).build();

        Assertions.assertEquals(3, p.x());
        Assertions.assertEquals(4, p.y());
    }

    /**
     * record: 记录: 一种轻量级的非可变的数据类, 在领域模型中可以当作值对象.
     *
     * @param x
     * @param y
     */
    @Builder
    record Point(double x, double y) {
    }

}
