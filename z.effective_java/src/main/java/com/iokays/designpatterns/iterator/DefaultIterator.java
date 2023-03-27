package com.iokays.designpatterns.iterator;

import java.util.Collection;

public abstract class DefaultIterator implements Iterator {

    private java.util.Iterator iterator;

    public DefaultIterator(Collection collection) {
        this(collection.iterator());
    }

    public DefaultIterator(java.util.Iterator iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Object next() {
        return iterator.next();
    }
}
