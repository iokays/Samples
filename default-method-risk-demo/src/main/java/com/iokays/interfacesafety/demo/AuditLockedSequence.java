package com.iokays.interfacesafety.demo;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

public final class AuditLockedSequence<E> implements FilterableSequence<E> {
    private final FilterableSequence<E> delegate;
    private final AtomicBoolean auditLocked;

    public AuditLockedSequence(FilterableSequence<E> delegate, AtomicBoolean auditLocked) {
        this.delegate = delegate;
        this.auditLocked = auditLocked;
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> delegateIterator = delegate.iterator();
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return delegateIterator.hasNext();
            }

            @Override
            public E next() {
                return delegateIterator.next();
            }

            @Override
            public void remove() {
                if (auditLocked.get()) {
                    throw new IllegalStateException("audit lock required before mutation");
                }
                delegateIterator.remove();
            }
        };
    }

    @Override
    public int size() {
        return delegate.size();
    }

    public boolean removeIfWithAuditLock(java.util.function.Predicate<? super E> filter) {
        auditLocked.set(false);
        try {
            return FilterableSequence.super.removeIf(filter);
        } finally {
            auditLocked.set(true);
        }
    }

    @Override
    public String toString() {
        return delegate.toString();
    }
}
