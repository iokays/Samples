package com.iokays.immutability.demo;

public final class MutableQuota {
    private int limit;
    private int used;

    public MutableQuota(int limit, int used) {
        validate(limit, used);
        this.limit = limit;
        this.used = used;
    }

    public int getLimit() {
        return limit;
    }

    public int getUsed() {
        return used;
    }

    public void setLimit(int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("limit must be >= 0");
        }
        this.limit = limit;
    }

    public void setUsed(int used) {
        if (used < 0) {
            throw new IllegalArgumentException("used must be >= 0");
        }
        this.used = used;
    }

    public boolean isValid() {
        return used <= limit;
    }

    public int remaining() {
        return limit - used;
    }

    @Override
    public String toString() {
        return "MutableQuota[limit=" + limit + ", used=" + used + "]";
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
