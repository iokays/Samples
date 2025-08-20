package com.iokays.sample.jdk9;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

/**
 * 在Junit5中, 类是不需要声明为public的, 但不可以声明为private
 */
class CollectionTest {

    /**
     * List.of(): 创建不可变的List列表
     */
    @Test
    void testList() {
        final List<Long> list = List.of(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L);
        //验证 List.of为不可变列表
        Assertions.assertThrows(UnsupportedOperationException.class, () -> Stream.of(11L).forEach(list::add));
    }

    /**
     * Set.of(): 创建不可变的Set列表
     */
    @Test
    void testSet() {
        final Set<Long> set = Set.of(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L);
        //验证 Set.of为不可变列表
        Assertions.assertThrows(UnsupportedOperationException.class, () -> Stream.of(11L).forEach(set::add));
    }

    /**
     * Map.of(): 创建不可变的Map列表
     */
    @Test
    void testMap() {
        final Map<String, Integer> map = Map.of("a", 1, "b", 2, "c", 3);
        //验证 Set.of为不可变列表
        Assertions.assertThrows(UnsupportedOperationException.class, () -> map.put("d", 4));
    }

}
