package com.iokays.common.core.specification;

/**
 * 规约模式接口
 * <p/>
 * 使用 {@link AbstractSpecification} 作为创建规约的基类，只需要实现 {@link #isSatisfiedBy(Object)} 方法即可。
 */
public interface Specification<T> {

    /**
     * 检查 {@code t} 是否满足规约。
     *
     * @param t 要检查的对象。
     * @return {@code true} 如果 {@code t} 满足规约。
     */
    boolean isSatisfiedBy(T t);

    /**
     * 创建一个新的规约，该规约是 {@code this} 规约和另一个规约的 AND 操作。
     *
     * @param specification 规约 AND。
     * @return 一个新的规约。
     */
    Specification<T> and(Specification<T> specification);

    /**
     * 创建一个新的规约，该规约是 {@code this} 规约和另一个规约的 OR 操作。
     *
     * @param specification 规约 OR。
     * @return 一个新的规约。
     */
    Specification<T> or(Specification<T> specification);

    /**
     * 创建一个新的规约，该规约是 {@code this} 规约的 NOT 操作。
     *
     * @param specification 规约 NOT。
     * @return 一个新的规约。
     */
    Specification<T> not(Specification<T> specification);
}
