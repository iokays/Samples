package com.iokays.interfacesafety.demo;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Predicate;

public interface FilterableSequence<E> extends Iterable<E> {
    @Override
    Iterator<E> iterator();

    int size();

    default boolean removeIf(Predicate<? super E> filter) {
        Objects.requireNonNull(filter);
        boolean result = false;
        for (Iterator<E> it = iterator(); it.hasNext(); ) {
            if (filter.test(it.next())) {
                it.remove();
                result = true;
            }
        }
        return result;
    }
}
