package com.iokays.common.jpa;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class JpaRegisterDeleteEventAspect {

    @Before("execution(* org.springframework.data.jpa.repository.JpaRepository+.delete(..)) && args(entity)")
    public void registerDeleteEvent(JoinPoint joinPoint, Object entity) {
        invokeDomainDeleteEvents(entity);
    }

    @Before("execution(* org.springframework.data.jpa.repository.JpaRepository+.deleteAll(..)) && args(entities)")
    public void registerDeleteEventsForBatch(JoinPoint joinPoint, Iterable<?> entities) {
        for (Object entity : entities) {
            invokeDomainDeleteEvents(entity);
        }
    }

    private void invokeDomainDeleteEvents(Object entity) {
        try {
            final Class<?> clazz = entity.getClass();

            if (!AbstractJpaAggregateRoot.class.isAssignableFrom(clazz)) {
                return;
            }

            for (Method method : clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent(JpaRegisterDeleteEvent.class)) {
                    if (method.getParameterCount() != 0 || method.getReturnType() != void.class) {
                        throw new RuntimeException("Method annotated with @RegisterDeleteEvent must be void and no-arg");
                    }
                    method.setAccessible(true);
                    method.invoke(entity);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to invoke @RegisterDeleteEvent method", e);
        }
    }
}
