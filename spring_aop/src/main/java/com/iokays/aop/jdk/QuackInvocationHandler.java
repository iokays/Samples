package com.iokays.aop.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 呱呱叫行为接口
 */
public class QuackInvocationHandler implements InvocationHandler {

    private final Object object;

    public QuackInvocationHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        final Object result = method.invoke(this.object, args);
        return result;
    }
}
