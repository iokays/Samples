package com.iokays.aop.jdk;


import io.swagger.annotations.ApiModel;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@ApiModel
public class QuackInvocationHandler implements InvocationHandler {

    private final Object object;

    public QuackInvocationHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("start");
        final Object result = method.invoke(this.object, args);
        System.out.println("end");
        return result;
    }
}
