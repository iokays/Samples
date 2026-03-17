package com.iokays.immutability.demo;

public final class QuotaEditor {
    private int limit;
    private int used;

    public QuotaEditor(int limit, int used) {
        validate(limit, used);
        this.limit = limit;
        this.used = used;
    }

    public QuotaEditor reserve(int units) {
        if (units <= 0) {
            throw new IllegalArgumentException("units must be > 0");
        }
        used += units;
        validate(limit, used);
        return this;
    }

    public QuotaEditor expand(int units) {
        if (units <= 0) {
            throw new IllegalArgumentException("units must be > 0");
        }
        limit += units;
        return this;
    }

    public QuotaSnapshot build() {
        return QuotaSnapshot.of(limit, used);
    }

    @Override
    public String toString() {
        return "QuotaEditor[limit=" + limit + ", used=" + used + "]";
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
