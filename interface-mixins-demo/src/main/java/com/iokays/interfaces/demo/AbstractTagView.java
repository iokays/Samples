package com.iokays.interfaces.demo;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public abstract class AbstractTagView implements Taggable {
    private final Set<String> tags;

    protected AbstractTagView(Set<String> tags) {
        this.tags = new LinkedHashSet<>(tags);
    }

    @Override
    public Set<String> tags() {
        return Collections.unmodifiableSet(tags);
    }

    @Override
    public String toString() {
        return label() + tags();
    }

    protected abstract String label();
}
