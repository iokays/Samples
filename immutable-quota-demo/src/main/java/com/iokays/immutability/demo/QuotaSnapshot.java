package com.iokays.immutability.demo;

import java.util.Objects;

public final class QuotaSnapshot {
    private final int limit;
    private final int used;

    private QuotaSnapshot(int limit, int used) {
        validate(limit, used);
        this.limit = limit;
        this.used = used;
    }

    public static QuotaSnapshot of(int limit, int used) {
        return new QuotaSnapshot(limit, used);
    }

    public int limit() {
        return limit;
    }

    public int used() {
        return used;
    }

    public int remaining() {
        return limit - used;
    }

    public QuotaSnapshot reserve(int units) {
        if (units <= 0) {
            throw new IllegalArgumentException("units must be > 0");
        }
        return new QuotaSnapshot(limit, used + units);
    }

    public QuotaSnapshot expand(int units) {
        if (units <= 0) {
            throw new IllegalArgumentException("units must be > 0");
        }
        return new QuotaSnapshot(limit + units, used);
    }

    public QuotaEditor toEditor() {
        return new QuotaEditor(limit, used);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QuotaSnapshot that)) {
            return false;
        }
        return limit == that.limit && used == that.used;
    }

    @Override
    public int hashCode() {
        return Objects.hash(limit, used);
    }

    @Override
    public String toString() {
        return "QuotaSnapshot[limit=" + limit + ", used=" + used + ", remaining=" + remaining() + "]";
    }

    private static void validate(int limit, int used) {
        if (limit < 0) {
            throw new IllegalArgumentException("limit must be >= 0");
        }
        if (used < 0 || used > limit) {
            throw new IllegalArgumentException("used must be between 0 and limit");
        }
    }
}
