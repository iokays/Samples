package com.iokays.common.core.specification;

/**
 * NOT 规约，用于创建一个规约的 NOT 规约。
 *
 * @param <T>
 */
public class NotSpecification<T> extends AbstractSpecification<T> {

    private final Specification<T> spec1;

    /**
     * 构造函数。
     * 创建一个新的 NOT 规约，该规约是 {@code spec1} 规约的 NOT 操作。
     *
     * @param spec1 规约 NOT。
     */
    public NotSpecification(final Specification<T> spec1) {
        this.spec1 = spec1;
    }

    /**
     * 检查 {@code t} 是否满足规约。
     *
     * @param t 要检查的对象。
     * @return
     */
    public boolean isSatisfiedBy(final T t) {
        return !spec1.isSatisfiedBy(t);
    }
}
