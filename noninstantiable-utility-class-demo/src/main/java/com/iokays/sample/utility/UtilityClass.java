package com.iokays.sample.utility;

/**
 * Noninstantiable utility class demonstrating Item 4 from Effective Java.
 * This class shows how to enforce noninstantiability using a private constructor.
 *
 * <p>Utility classes are not designed to be instantiated. They group related
 * static methods and static fields, similar to java.lang.Math or java.util.Arrays.
 *
 * <p>Attempting to create an abstract class to enforce noninstantiability doesn't work,
 * as the class can be subclassed and instantiated. The proper approach is to include
 * a private constructor that throws an AssertionError.
 *
 * @author iokays
 * @see java.lang.Math
 * @see java.util.Arrays
 * @see java.util.Collections
 */
public final class UtilityClass {

    // Static constants for utility operations
    public static final double PI = 3.141592653589793;
    public static final double E = 2.718281828459045;

    /**
     * Suppress default constructor for noninstantiability.
     * This constructor is private to prevent external instantiation.
     * The AssertionError ensures the constructor is never accidentally
     * called from within the class.
     */
    private UtilityClass() {
        throw new AssertionError("Utility class should not be instantiated");
    }

    /**
     * Returns the maximum of two integers.
     *
     * @param a the first integer
     * @param b the second integer
     * @return the larger of the two integers
     */
    public static int max(int a, int b) {
        return a > b ? a : b;
    }

    /**
     * Returns the minimum of two integers.
     *
     * @param a the first integer
     * @param b the second integer
     * @return the smaller of the two integers
     */
    public static int min(int a, int b) {
        return a < b ? a : b;
    }

    /**
     * Calculates the sum of all integers in an array.
     *
     * @param numbers the array of integers
     * @return the sum of all elements
     * @throws IllegalArgumentException if the array is null
     */
    public static int sum(int[] numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        int total = 0;
        for (int num : numbers) {
            total += num;
        }
        return total;
    }

    /**
     * Calculates the average of all integers in an array.
     *
     * @param numbers the array of integers
     * @return the average as a double
     * @throws IllegalArgumentException if the array is null or empty
     */
    public static double average(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        return (double) sum(numbers) / numbers.length;
    }

    /**
     * Checks if a string is null or empty.
     *
     * @param str the string to check
     * @return true if the string is null or empty, false otherwise
     */
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * Returns a non-null string, or a default if the input is null.
     *
     * @param str the input string
     * @param defaultValue the default value to return if str is null
     * @return the input string or the default value
     */
    public static String defaultIfNull(String str, String defaultValue) {
        return str != null ? str : defaultValue;
    }

    /**
     * Reverses a string.
     *
     * @param str the string to reverse
     * @return the reversed string, or null if input is null
     */
    public static String reverse(String str) {
        if (str == null) {
            return null;
        }
        return new StringBuilder(str).reverse().toString();
    }

    /**
     * Clamps a value between a minimum and maximum.
     *
     * @param value the value to clamp
     * @param min the minimum allowed value
     * @param max the maximum allowed value
     * @return the clamped value
     */
    public static int clamp(int value, int min, int max) {
        return value < min ? min : (value > max ? max : value);
    }
}
