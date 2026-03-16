package com.iokays.comparable.demo;

import java.math.BigDecimal;
import java.util.Objects;

public final class PriceQuote implements Comparable<PriceQuote> {
    private final BigDecimal amount;

    public PriceQuote(String amount) {
        this.amount = new BigDecimal(amount);
    }

    public BigDecimal amount() {
        return amount;
    }

    @Override
    public int compareTo(PriceQuote other) {
        return amount.compareTo(Objects.requireNonNull(other, "other").amount);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof PriceQuote that)) {
            return false;
        }
        return amount.equals(that.amount);
    }

    @Override
    public int hashCode() {
        return amount.hashCode();
    }

    @Override
    public String toString() {
        return amount.toPlainString();
    }
}
