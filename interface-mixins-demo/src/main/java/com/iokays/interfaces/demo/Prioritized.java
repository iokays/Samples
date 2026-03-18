package com.iokays.interfaces.demo;

public interface Prioritized {
    int priority();

    default boolean isUrgent() {
        return priority() >= 8;
    }
}
