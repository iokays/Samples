package com.iokays.designpattern.specification;

public class OrSpecification<T> implements Specification<T> {
    private final Specification<T> spec1;
    private final Specification<T> spec2;

    public OrSpecification(Specification<T> spec1, Specification<T> spec2) {
        this.spec1 = spec1;
        this.spec2 = spec2;
    }

    @Override
    public boolean isSatisfiedBy(T item) {
        return spec1.isSatisfiedBy(item) || spec2.isSatisfiedBy(item);
    }
}