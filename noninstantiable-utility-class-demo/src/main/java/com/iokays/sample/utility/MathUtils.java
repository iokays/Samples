package com.iokays.sample.utility;

/**
 * Another example of a noninstantiable utility class for mathematical operations.
 * This class demonstrates that the pattern can be applied to various domains.
 *
 * <p>This class groups mathematical utility methods similar to java.lang.Math,
 * but with additional convenience methods not found in the standard library.
 *
 * @author iokays
 */
public final class MathUtils {

    /**
     * Suppress default constructor for noninstantiability.
     */
    private MathUtils() {
        throw new AssertionError("MathUtils should not be instantiated");
    }

    /**
     * Checks if a number is even.
     *
     * @param n the number to check
     * @return true if the number is even
     */
    public static boolean isEven(int n) {
        return n % 2 == 0;
    }

    /**
     * Checks if a number is odd.
     *
     * @param n the number to check
     * @return true if the number is odd
     */
    public static boolean isOdd(int n) {
        return n % 2 != 0;
    }

    /**
     * Checks if a number is prime.
     *
     * @param n the number to check
     * @return true if the number is prime
     */
    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        if (n <= 3) {
            return true;
        }
        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Calculates the factorial of a non-negative integer.
     *
     * @param n the non-negative integer
     * @return the factorial of n
     * @throws IllegalArgumentException if n is negative
     */
    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers");
        }
        if (n <= 1) {
            return 1;
        }
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    /**
     * Calculates the greatest common divisor of two integers.
     *
     * @param a the first integer
     * @param b the second integer
     * @return the greatest common divisor
     */
    public static int gcd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    /**
     * Calculates the least common multiple of two integers.
     *
     * @param a the first integer
     * @param b the second integer
     * @return the least common multiple
     */
    public static int lcm(int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        }
        return Math.abs(a * b) / gcd(a, b);
    }

    /**
     * Returns the Fibonacci number at the specified position.
     *
     * @param n the position (0-indexed)
     * @return the Fibonacci number
     * @throws IllegalArgumentException if n is negative
     */
    public static long fibonacci(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Position must be non-negative");
        }
        if (n <= 1) {
            return n;
        }
        long prev = 0, curr = 1;
        for (int i = 2; i <= n; i++) {
            long next = prev + curr;
            prev = curr;
            curr = next;
        }
        return curr;
    }

    /**
     * Checks if a number is a perfect square.
     *
     * @param n the number to check
     * @return true if the number is a perfect square
     */
    public static boolean isPerfectSquare(int n) {
        if (n < 0) {
            return false;
        }
        int sqrt = (int) Math.sqrt(n);
        return sqrt * sqrt == n;
    }
}
