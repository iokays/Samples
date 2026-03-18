package com.iokays.interfaces.demo;

public abstract class TimedTaskBase {
    private final int dueHour;

    protected TimedTaskBase(int dueHour) {
        this.dueHour = dueHour;
    }

    public int dueHour() {
        return dueHour;
    }
}
