package com.iokays.sample.utility;

import java.util.regex.Pattern;

/**
 * A utility class for string validation operations.
 * Demonstrates the noninstantiable utility class pattern for validation purposes.
 *
 * <p>This class groups common validation methods that can be reused across
 * different parts of an application, similar to how validation frameworks
 * provide static utility methods.
 *
 * @author iokays
 */
public final class StringValidator {

    // Pre-compiled patterns for performance
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
    );
    private static final Pattern PHONE_PATTERN = Pattern.compile(
        "^\\+?[0-9]{10,15}$"
    );
    private static final Pattern CHINESE_PHONE_PATTERN = Pattern.compile(
        "^1[3-9]\\d{9}$"
    );

    /**
     * Suppress default constructor for noninstantiability.
     */
    private StringValidator() {
        throw new AssertionError("StringValidator should not be instantiated");
    }

    /**
     * Validates if a string is a valid email address.
     *
     * @param email the email address to validate
     * @return true if the email is valid
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }

    /**
     * Validates if a string is a valid phone number (international format).
     *
     * @param phone the phone number to validate
     * @return true if the phone number is valid
     */
    public static boolean isValidPhone(String phone) {
        if (phone == null || phone.isEmpty()) {
            return false;
        }
        return PHONE_PATTERN.matcher(phone).matches();
    }

    /**
     * Validates if a string is a valid Chinese mobile phone number.
     *
     * @param phone the phone number to validate
     * @return true if the phone number is a valid Chinese mobile number
     */
    public static boolean isValidChinesePhone(String phone) {
        if (phone == null || phone.isEmpty()) {
            return false;
        }
        return CHINESE_PHONE_PATTERN.matcher(phone).matches();
    }

    /**
     * Checks if a string contains only digits.
     *
     * @param str the string to check
     * @return true if the string contains only digits
     */
    public static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if a string contains only ASCII letters (a-z, A-Z).
     *
     * @param str the string to check
     * @return true if the string contains only ASCII letters
     */
    public static boolean isAlpha(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if a string contains only alphanumeric characters.
     *
     * @param str the string to check
     * @return true if the string is alphanumeric
     */
    public static boolean isAlphanumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if a string is within a specified length range.
     *
     * @param str the string to check
     * @param minLength the minimum length (inclusive)
     * @param maxLength the maximum length (inclusive)
     * @return true if the string length is within the range
     */
    public static boolean isLengthInRange(String str, int minLength, int maxLength) {
        if (str == null) {
            return minLength <= 0;
        }
        int length = str.length();
        return length >= minLength && length <= maxLength;
    }

    /**
     * Checks if a string contains any Chinese characters.
     *
     * @param str the string to check
     * @return true if the string contains Chinese characters
     */
    public static boolean containsChinese(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (char c : str.toCharArray()) {
            // Unicode range for Chinese characters
            if (c >= '\u4e00' && c <= '\u9fa5') {
                return true;
            }
        }
        return false;
    }
}
