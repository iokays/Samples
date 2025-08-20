package com.iokays.core.generics;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 泛型接口 测试
 */
public class GenericsInterfaceTest {

    @Test
    void test() {
        final GenericInterface<String> genericInterface = new GenericProcessor<String>();
        genericInterface.performAction("hello");
        Assertions.assertInstanceOf(String.class, genericInterface.getResult());
        Assertions.assertEquals("hello", genericInterface.getResult());
    }

}


interface GenericInterface<T> {
    void performAction(T action);
    T getResult();
}

/**
 * 泛型接口实现类
 * @param <T>
 */
class GenericProcessor<T> implements GenericInterface<T> {

    private T data;

    @Override
    public void performAction(T action) {
        this.data = action;
    }

    @Override
    public T getResult() {
        return data;
    }
}
