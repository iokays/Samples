package com.iokays.hashcode.correct;

/**
 * A value object that keeps equals and hashCode aligned.
 */
public final class PhoneNumber {
    private final short areaCode;
    private final short prefix;
    private final short lineNum;

    public PhoneNumber(int areaCode, int prefix, int lineNum) {
        this.areaCode = rangeCheck(areaCode, 999, "area code");
        this.prefix = rangeCheck(prefix, 999, "prefix");
        this.lineNum = rangeCheck(lineNum, 9999, "line number");
    }

    private static short rangeCheck(int value, int max, String name) {
        if (value < 0 || value > max) {
            throw new IllegalArgumentException(name + ": " + value);
        }
        return (short) value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
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
        return "%03d-%03d-%04d".formatted((int) areaCode, (int) prefix, (int) lineNum);
    }
}
