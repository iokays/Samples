package com.iokays.interfaces.demo;

import java.util.Set;

public interface Taggable {
    Set<String> tags();

    default boolean hasTag(String tag) {
        return tags().contains(tag);
    }
}
