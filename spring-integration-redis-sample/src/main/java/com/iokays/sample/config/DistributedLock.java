package com.iokays.sample.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.temporal.ChronoUnit;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface DistributedLock {

    /**
     * 锁实例的名称
     *
     * @return 指定锁名称
     */
    String value() default "";

    /**
     * 用于动态计算key的Spring表达式
     *
     * @return Spring Expression Language (SpEL) expression for computing the key dynamically.
     */
    String key() default "";

    /**
     * 等待锁的最大时间
     *
     * @return the maximum time to wait for the lock
     */
    long time() default 0;

    /**
     * 时间单位
     *
     * @return he time unit of the time argument
     */
    ChronoUnit unit() default ChronoUnit.SECONDS;

}
