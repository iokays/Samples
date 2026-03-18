package com.iokays.interfacesafety.demo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class ArraySequence<E> implements FilterableSequence<E> {
    private final List<E> values;

    public ArraySequence(List<E> values) {
        this.values = new ArrayList<>(values);
    }

    @Override
    public Iterator<E> iterator() {
        return values.iterator();
    }

    @Override
    public int size() {
        return values.size();
    }

    @Override
    public String toString() {
        return values.toString();
    }
}
