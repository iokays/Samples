package com.iokays.core.generics;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型通配符 测试
 */
public class GenericsWildcardTest {

    //无界通配符
    @Test
    void testUnboundedWildcard() {
        final List<Object> objects = new ArrayList<>(); //可以放入任意类型
        objects.add("hello");
        objects.add(1L);


        //未知, 可以指向任意list
        final List<?> list = objects;
        // 但定义之后, add 只能放 null.
        list.add(null);

        final Object first = list.getFirst(); //只能是Object

        Assertions.assertInstanceOf(String.class, list.getFirst());
        Assertions.assertNull(list.getLast());

        // list.add(String.valueOf("111")); //编译报错
    }

    /**
     * 上界通配符 <? extends T>:
     * <? extends T> 表示 "某种未知类型，但已知它是 T 或其子类"
     * 表示 T 或其子类
     * 安全读取：可以读取为 T
     * 不安全写入：不能添加元素（除了 null）
     */
    @Test
    void testUpperBoundedWildcard() {
        //这个就定义了上界, 只能是 Number 及其子类, 但是你不能插入任何元素包括 Number和子类, 因为 new 是一个 ArrayList<Integer>.
        final List<? extends Number> numbers = new ArrayList<Integer>();
        numbers.add(null);  //可以放null
        final Number first = numbers.getFirst();
    }

    /**
     * 下界通配符 <? super T>
     * 某种未知类型，但已知它是 T 或其父类"
     * 安全写入：可以添加 T 及其子类
     * 不安全读取：只能读取为 Object
     */
    @Test
    void testLowerBoundedWildcard() {
        //实际可能是 List<Integer>, List<Number>, List<Object>
        final List<? super Integer> numbers = new ArrayList<Number>();
        //插入 Integer 及其子类, 满足 instanceof <? super Integer>
        numbers.add(1);
        //但是取出来, 就不知道具体的类型了.
        final Object first = numbers.getFirst();
    }
}


