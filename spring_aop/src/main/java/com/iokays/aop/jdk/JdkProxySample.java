package com.iokays.aop.jdk;

import com.iokays.design.pattern.delegate.QuackBehavior;
import io.swagger.annotations.ApiModel;

import java.lang.reflect.Proxy;

@ApiModel(description = "JDK动态代理实例")
public class JdkProxySample {

    public static void main(String[] args) {

        final QuackBehavior quack = () -> System.out.println("呱呱叫");

        final QuackInvocationHandler quackInvocationHandler = new QuackInvocationHandler(quack);

        final QuackBehavior duck = (QuackBehavior) Proxy.newProxyInstance(
                quack.getClass().getClassLoader(),
                new Class[]{QuackBehavior.class},
                quackInvocationHandler);

        duck.quack();

    }

}
