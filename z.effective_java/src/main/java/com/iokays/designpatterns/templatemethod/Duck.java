package com.iokays.designpatterns.templatemethod;

import java.util.StringJoiner;

public class Duck implements Comparable {

    String name;
    int weight;

    public Duck(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Duck.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("weight=" + weight)
                .toString();
    }

    @Override
    public int compareTo(Object o) {
        Duck otherDuck = (Duck) o;

        if (this.weight < otherDuck.weight) {
            return -1;
        } else if (this.weight == otherDuck.weight) {
            return 0;
        } else {
            return 1;
        }
    }
}
