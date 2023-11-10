package com.iokays.designpattern.specification;

import java.util.function.Predicate;

public class AgePredicate implements Predicate<Person> {

    private int minAge;

    public AgePredicate(int minAge) {
        this.minAge = minAge;
    }

    @Override
    public boolean test(Person person) {
        return person.age() >= minAge;
    }
}