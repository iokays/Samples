package com.iokays.common.core.specification;

/**
 * 抽象的规约基类，实现了 {@code and}, {@code or} 和 {@code not} 方法。
 */
public abstract class AbstractSpecification<T> implements Specification<T> {

    /**
     * 创建一个新的规约，该规约是 {@code this} 规约和另一个规约的 AND 操作。
     *
     * @param specification 规约 AND。
     * @return 一个新的规约。
     */
    public Specification<T> and(final Specification<T> specification) {
        return new AndSpecification<>(this, specification);
    }

    /**
     * 创建一个新的规约，该规约是 {@code this} 规约和另一个规约的 OR 操作。
     *
     * @param specification 规约 OR。
     * @return 一个新的规约。
     */
    public Specification<T> or(final Specification<T> specification) {
        return new OrSpecification<>(this, specification);
    }

    /**
     * 创建一个新的规约，该规约是 {@code this} 规约的 NOT 操作。
     *
     * @param specification 规约 NOT。
     * @return 一个新的规约。
     */
    public Specification<T> not(final Specification<T> specification) {
        return new NotSpecification<>(specification);
    }
}