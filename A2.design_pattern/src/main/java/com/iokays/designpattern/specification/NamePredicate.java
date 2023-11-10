package com.iokays.designpattern.specification;

import java.util.function.Predicate;

public class NamePredicate implements Predicate<Person> {
    private String name;

    public NamePredicate(String name) {
        this.name = name;
    }

    @Override
    public boolean test(Person person) {
        return person.name().equals(name);
    }
}