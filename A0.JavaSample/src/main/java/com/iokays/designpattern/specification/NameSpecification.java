package com.iokays.designpattern.specification;

public class NameSpecification implements Specification<Person> {
    private String name;

    public NameSpecification(String name) {
        this.name = name;
    }

    @Override
    public boolean isSatisfiedBy(Person person) {
        return person.name().equals(name);
    }
}