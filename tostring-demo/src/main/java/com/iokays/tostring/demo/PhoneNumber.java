package com.iokays.tostring.demo;

import java.util.Objects;

public final class PhoneNumber {
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

    public short areaCode() {
        return areaCode;
    }

    public short prefix() {
        return prefix;
    }

    public short lineNum() {
        return lineNum;
    }

    public static PhoneNumber parse(String formatted) {
        Objects.requireNonNull(formatted, "formatted");
        if (!formatted.matches("\\d{3}-\\d{3}-\\d{4}")) {
            throw new IllegalArgumentException("Expected format XXX-YYY-ZZZZ: " + formatted);
        }
        return new PhoneNumber(
                Integer.parseInt(formatted.substring(0, 3)),
                Integer.parseInt(formatted.substring(4, 7)),
                Integer.parseInt(formatted.substring(8, 12))
        );
    }

    @Override
    public String toString() {
        return "%03d-%03d-%04d".formatted(areaCode, prefix, lineNum);
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

    private static void validatePart(int value, int max, String fieldName) {
        if (value < 0 || value > max) {
            throw new IllegalArgumentException(fieldName + " out of range: " + value);
        }
    }
}
