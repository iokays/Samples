package com.iokays.common.core.specification;

/**
 * OR 规约，用于创建两个规约的 OR 规约。
 *
 * @param <T>
 */
public class OrSpecification<T> extends AbstractSpecification<T> {

    private final Specification<T> spec1;
    private final Specification<T> spec2;

    /**
     * 构造函数。
     * 创建一个新的 OR 规约，该规约是 {@code spec1} 规约和 {@code spec2} 规约的 OR 操作。
     *
     * @param spec1
     * @param spec2
     */
    public OrSpecification(final Specification<T> spec1, final Specification<T> spec2) {
        this.spec1 = spec1;
        this.spec2 = spec2;
    }

    /**
     * 检查 {@code t} 是否满足规约。
     *
     * @param t 要检查的对象。
     * @return
     */
    public boolean isSatisfiedBy(final T t) {
        return spec1.isSatisfiedBy(t) || spec2.isSatisfiedBy(t);
    }
}
