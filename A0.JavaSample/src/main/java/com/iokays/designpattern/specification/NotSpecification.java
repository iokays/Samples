package com.iokays.designpattern.specification;

public class NotSpecification<T> implements Specification<T> {
    private final Specification<T> spec1;

    public NotSpecification(Specification<T> spec1) {
        this.spec1 = spec1;
    }

    @Override
    public boolean isSatisfiedBy(T item) {
        return !spec1.isSatisfiedBy(item);
    }
}