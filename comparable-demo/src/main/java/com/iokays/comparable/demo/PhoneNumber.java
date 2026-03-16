package com.iokays.comparable.demo;

import java.util.Comparator;
import java.util.Objects;

public final class PhoneNumber implements Comparable<PhoneNumber> {
    private static final Comparator<PhoneNumber> COMPARATOR =
            Comparator.comparingInt((PhoneNumber pn) -> pn.areaCode)
                    .thenComparingInt(pn -> pn.prefix)
                    .thenComparingInt(pn -> pn.lineNum);

    private final short areaCode;
    private final short prefix;
    private final short lineNum;

    public PhoneNumber(int areaCode, int prefix, int lineNum) {
        validatePart(areaCode, 999, "areaCode");
        validatePart(prefix, 999, "prefix");
        validatePart(lineNum, 9999, "lineNum");
        this.areaCode = (short) areaCode;
        this.prefix = (short) prefix;
        this.lineNum = (short) lineNum;
    }

    @Override
    public int compareTo(PhoneNumber other) {
        return COMPARATOR.compare(this, Objects.requireNonNull(other, "other"));
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof PhoneNumber that)) {
            return false;
        }
        return areaCode == that.areaCode
                && prefix == that.prefix
                && lineNum == that.lineNum;
    }

    @Override
    public int hashCode() {
        int result = Short.hashCode(areaCode);
        result = 31 * result + Short.hashCode(prefix);
        result = 31 * result + Short.hashCode(lineNum);
        return result;
    }

    @Override
    public String toString() {
        return "%03d-%03d-%04d".formatted(areaCode, prefix, lineNum);
    }

    private static void validatePart(int value, int max, String fieldName) {
        if (value < 0 || value > max) {
            throw new IllegalArgumentException(fieldName + " out of range: " + value);
        }
    }
}
