package com.iokays.sample.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.integration.support.locks.LockRegistry;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;
import java.time.Duration;
import java.util.Objects;

/**
 * 分布式锁切面实现
 * 基于切面的实现，是因为在释放锁需在提交事务(声明注解性事务)之后。
 * 备注: 事务生效时机是在第一次读、写表（InnoDB）的操作开始。
 */
@Aspect
@Component
public class DistributedLockAspect {

    private static final Logger log = LoggerFactory.getLogger(DistributedLockAspect.class);

    /**
     * Spring 集成的 LockRegistry
     */
    private final ApplicationContext applicationContext;

    private final DistributedLockExpressionEvaluator evaluator = new DistributedLockExpressionEvaluator();

    public DistributedLockAspect(final ApplicationContext applicationContext) {
        this.applicationContext = Objects.requireNonNull(applicationContext);
    }

    /**
     * Spring 环绕通知 实现分布式锁调用
     */
    @Around("@annotation(distributedLock)")
    public Object around(final ProceedingJoinPoint pjp, final DistributedLock distributedLock) throws Throwable {
        Validate.notNull(pjp, "pjp is null");
        Validate.notNull(distributedLock, "distributedLock is null");
        Validate.noNullElements(new Object[]{distributedLock.key(), distributedLock.time(), distributedLock.unit()});

        final String key = getKey(distributedLock.key(), pjp);
        log.info("distributedLock: {}, key: {}", distributedLock, key);

        final var value = distributedLock.value();

        final var lockRegistry = StringUtils.isNotBlank(value) ?
                applicationContext.getBean(value, LockRegistry.class) : applicationContext.getBean(LockRegistry.class);

        return distributedLock.time() == 0L ?
                lockRegistry.executeLocked(key, () -> pjp.proceed()) :
                lockRegistry.executeLocked(key, Duration.of(distributedLock.time(), distributedLock.unit()), () -> pjp.proceed());
    }

    private String getKey(final String key, final ProceedingJoinPoint pjp) {
        final var signature = (MethodSignature) pjp.getSignature();
        final var method = BridgeMethodResolver.findBridgedMethod(signature.getMethod());

        final var target = pjp.getTarget();
        final var targetClass = AopProxyUtils.ultimateTargetClass(target);

        final var targetMethod = (!Proxy.isProxyClass(targetClass) ?
                AopUtils.getMostSpecificMethod(method, targetClass) : method);

        final var methodKey = new AnnotatedElementKey(targetMethod, targetClass);

        final var evaluationContext = evaluator.createMethodBasedEvaluationContext(targetMethod, pjp.getArgs());
        final Object result = evaluator.eval(key, methodKey, evaluationContext);
        return Objects.requireNonNull(result).toString();
    }

}
