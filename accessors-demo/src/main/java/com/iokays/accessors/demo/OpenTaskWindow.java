package com.iokays.accessors.demo;

public final class OpenTaskWindow {
    public int startHour;
    public int endHour;

    public OpenTaskWindow(int startHour, int endHour) {
        validateHour(startHour, "startHour");
        validateHour(endHour, "endHour");
        if (endHour <= startHour) {
            throw new IllegalArgumentException("endHour must be greater than startHour");
        }
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public int durationMinutes() {
        return (endHour - startHour) * 60;
    }

    public boolean isValid() {
        return startHour >= 0 && startHour < 24
                && endHour >= 0 && endHour < 24
                && endHour > startHour;
    }

    private static void validateHour(int value, String fieldName) {
        if (value < 0 || value >= 24) {
            throw new IllegalArgumentException(fieldName + " out of range: " + value);
        }
    }
}
