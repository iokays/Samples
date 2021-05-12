package com.iokays.spring.aop;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

public class SpringAopPointcutSample {

    public static void main(String[] args) {
        final var proxyFactory = new ProxyFactory();
        proxyFactory.addAdvisor(new DefaultPointcutAdvisor(new FooStaticPointcut(), new FooInterceptor()));
        proxyFactory.setTarget(new Foobar());
        Foo foo = (Foobar)proxyFactory.getProxy();
        foo.foo();
    }

}

interface Foo {
    void foo();
}

class Foobar implements Foo {
    @Override
    public void foo() {
        System.out.println("foobar");
    }
}

class FooInterceptor implements MethodInterceptor  {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("start");
        var retVal= invocation.proceed();
        System.out.println("end");
        return retVal;
    }
}


class FooStaticPointcut extends StaticMethodMatcherPointcut {

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return "foo".matches(method.getName());
    }

    @Override
    public ClassFilter getClassFilter() {
        return cls -> cls == Foobar.class;
    }

}



