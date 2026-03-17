package com.iokays.accessors.demo;

public final class ImmutableTimePoint {
    public final int hour;
    public final int minute;

    public ImmutableTimePoint(int hour, int minute) {
        if (hour < 0 || hour >= 24) {
            throw new IllegalArgumentException("hour out of range: " + hour);
        }
        if (minute < 0 || minute >= 60) {
            throw new IllegalArgumentException("minute out of range: " + minute);
        }
        this.hour = hour;
        this.minute = minute;
    }

    @Override
    public String toString() {
        return "%02d:%02d".formatted(hour, minute);
    }
}
