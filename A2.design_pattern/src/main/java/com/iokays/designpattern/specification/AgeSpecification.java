package com.iokays.designpattern.specification;

public class AgeSpecification implements Specification<Person> {

    private int minAge;

    public AgeSpecification(int minAge) {
        this.minAge = minAge;
    }

    @Override
    public boolean isSatisfiedBy(Person person) {
        return person.age() >= minAge;
    }
}