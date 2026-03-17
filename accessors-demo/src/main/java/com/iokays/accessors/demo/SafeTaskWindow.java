package com.iokays.accessors.demo;

public final class SafeTaskWindow {
    private int startHour;
    private int endHour;

    public SafeTaskWindow(int startHour, int endHour) {
        validateRange(startHour, endHour);
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public int getStartHour() {
        return startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setStartHour(int startHour) {
        validateRange(startHour, this.endHour);
        this.startHour = startHour;
    }

    public void setEndHour(int endHour) {
        validateRange(this.startHour, endHour);
        this.endHour = endHour;
    }

    public int durationMinutes() {
        return (endHour - startHour) * 60;
    }

    @Override
    public String toString() {
        return "SafeTaskWindow[startHour=%d, endHour=%d]".formatted(startHour, endHour);
    }

    private static void validateRange(int startHour, int endHour) {
        validateHour(startHour, "startHour");
        validateHour(endHour, "endHour");
        if (endHour <= startHour) {
            throw new IllegalArgumentException("endHour must be greater than startHour");
        }
    }

    private static void validateHour(int value, String fieldName) {
        if (value < 0 || value >= 24) {
            throw new IllegalArgumentException(fieldName + " out of range: " + value);
        }
    }
}
