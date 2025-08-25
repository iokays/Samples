package com.iokays.common.core.modelling;

/**
 * 用于创建对象修改器的接口。
 *
 * @param <TO_MUTATE> 要执行变化的类类型
 */
@FunctionalInterface
public interface Modeller<TO_MUTATE> {

    /**
     * 将变化应用到对象上。
     *
     * @param from 要应用变化的对象
     * @return 变化后的对象
     */
    TO_MUTATE to(TO_MUTATE from);
}
