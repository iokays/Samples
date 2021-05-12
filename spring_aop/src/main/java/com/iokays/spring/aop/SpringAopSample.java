package com.iokays.spring.aop;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;


public class SpringAopSample {
    public static void main(String[] args) {
        final var target = new Agent();

        int mark = 0;
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvice(new AfterReturningExceptionAdvice());
        proxyFactory.setTarget(target);

        Agent proxy = (Agent) proxyFactory.getProxy();
        try {
            mark = proxy.one();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(mark);
    }
}


class AfterReturningExceptionAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        throw new RuntimeException("create exception.");
    }
}


class TracingInterceptor implements MethodInterceptor {
    public Object invoke(MethodInvocation i) throws Throwable {
        System.out.println("method "+i.getMethod()+" is called on "+ i.getThis()+" with args "+i.getArguments());
        Object ret=i.proceed();
        System.out.println("method "+i.getMethod()+" returns "+ret);
        return ret;
    }
}


class Agent {
    public void speak() {
        System.out.println("Bond");
    }

    public void foo() {
        System.out.println("Foo");
    }

    public int one() {
        return 1;
    }
}

