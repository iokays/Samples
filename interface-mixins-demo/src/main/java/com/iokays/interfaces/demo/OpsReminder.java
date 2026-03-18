package com.iokays.interfaces.demo;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public final class OpsReminder extends TimedTaskBase implements Taggable, Prioritized {
    private final Set<String> tags;
    private final int priority;

    public OpsReminder(int dueHour, Set<String> tags, int priority) {
        super(dueHour);
        this.tags = new LinkedHashSet<>(tags);
        this.priority = priority;
    }

    @Override
    public Set<String> tags() {
        return Collections.unmodifiableSet(tags);
    }

    @Override
    public int priority() {
        return priority;
    }
}
