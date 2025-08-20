package com.iokays.sample.jdk9;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class StreamTest {

    /**
     * takeWhile: 从流中提取元素，直到遇到第一个不满足条件的元素，然后立即停止（类似 break）。
     */
    @Test
    void testTakeWhile() {
        final List<Long> list = List.of(1L, 2L, 3L, 4L, 5L);
        final List<Long> result = list.stream().takeWhile(n -> n < 4).toList();
        Assertions.assertEquals(3, result.size());
        Assertions.assertTrue(List.of(1L, 2L, 3L).containsAll(result));
    }


    /**
     * dropWhile: 从流中跳过元素，直到遇到第一个不满足条件的元素，然后保留剩余所有元素（包括不满足条件的元素）。
     */
    @Test
    void testDropWhile() {
        final List<Long> list = List.of(1L, 2L, 3L, 4L, 5L);
        final List<Long> result = list.stream().dropWhile(n -> n < 4).toList();
        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(List.of(4L, 5L).containsAll(result));
    }

}
