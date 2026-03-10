package com.iokays.sample.utility;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for MathUtils demonstrating noninstantiability.
 */
@DisplayName("MathUtils Tests")
class MathUtilsTest {

    @Test
    @DisplayName("Private constructor should throw AssertionError when accessed via reflection")
    void privateConstructorShouldThrowAssertionError() throws Exception {
        Constructor<MathUtils> constructor = MathUtils.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        InvocationTargetException exception = assertThrows(
            InvocationTargetException.class,
            constructor::newInstance
        );

        assertTrue(exception.getCause() instanceof AssertionError);
        assertTrue(exception.getCause().getMessage().contains("should not be instantiated"));
    }

    @Test
    @DisplayName("isEven() should correctly identify even numbers")
    void testIsEven() {
        assertTrue(MathUtils.isEven(0));
        assertTrue(MathUtils.isEven(2));
        assertTrue(MathUtils.isEven(-4));
        assertTrue(MathUtils.isEven(100));
        assertFalse(MathUtils.isEven(1));
        assertFalse(MathUtils.isEven(-3));
        assertFalse(MathUtils.isEven(99));
    }

    @Test
    @DisplayName("isOdd() should correctly identify odd numbers")
    void testIsOdd() {
        assertFalse(MathUtils.isOdd(0));
        assertFalse(MathUtils.isOdd(2));
        assertTrue(MathUtils.isOdd(1));
        assertTrue(MathUtils.isOdd(-3));
        assertTrue(MathUtils.isOdd(99));
    }

    @Test
    @DisplayName("isPrime() should correctly identify prime numbers")
    void testIsPrime() {
        assertFalse(MathUtils.isPrime(0));
        assertFalse(MathUtils.isPrime(1));
        assertTrue(MathUtils.isPrime(2));
        assertTrue(MathUtils.isPrime(3));
        assertFalse(MathUtils.isPrime(4));
        assertTrue(MathUtils.isPrime(5));
        assertTrue(MathUtils.isPrime(7));
        assertFalse(MathUtils.isPrime(9));
        assertTrue(MathUtils.isPrime(11));
        assertTrue(MathUtils.isPrime(13));
        assertFalse(MathUtils.isPrime(15));
        assertTrue(MathUtils.isPrime(17));
        assertTrue(MathUtils.isPrime(19));
        assertFalse(MathUtils.isPrime(21));
    }

    @Test
    @DisplayName("factorial() should calculate factorial correctly")
    void testFactorial() {
        assertEquals(1, MathUtils.factorial(0));
        assertEquals(1, MathUtils.factorial(1));
        assertEquals(2, MathUtils.factorial(2));
        assertEquals(6, MathUtils.factorial(3));
        assertEquals(24, MathUtils.factorial(4));
        assertEquals(120, MathUtils.factorial(5));
        assertEquals(720, MathUtils.factorial(6));
    }

    @Test
    @DisplayName("factorial() should throw exception for negative numbers")
    void testFactorialNegative() {
        assertThrows(IllegalArgumentException.class, () -> MathUtils.factorial(-1));
        assertThrows(IllegalArgumentException.class, () -> MathUtils.factorial(-10));
    }

    @Test
    @DisplayName("gcd() should calculate greatest common divisor correctly")
    void testGcd() {
        assertEquals(6, MathUtils.gcd(12, 18));
        assertEquals(1, MathUtils.gcd(7, 11));
        assertEquals(5, MathUtils.gcd(25, 15));
        assertEquals(12, MathUtils.gcd(12, 0));
        assertEquals(12, MathUtils.gcd(0, 12));
        assertEquals(1, MathUtils.gcd(1, 1));
        assertEquals(3, MathUtils.gcd(-6, 9)); // Absolute values
    }

    @Test
    @DisplayName("lcm() should calculate least common multiple correctly")
    void testLcm() {
        assertEquals(36, MathUtils.lcm(12, 18));
        assertEquals(77, MathUtils.lcm(7, 11));
        assertEquals(75, MathUtils.lcm(25, 15));
        assertEquals(0, MathUtils.lcm(0, 12));
        assertEquals(0, MathUtils.lcm(12, 0));
        assertEquals(1, MathUtils.lcm(1, 1));
    }

    @Test
    @DisplayName("fibonacci() should calculate Fibonacci numbers correctly")
    void testFibonacci() {
        assertEquals(0, MathUtils.fibonacci(0));
        assertEquals(1, MathUtils.fibonacci(1));
        assertEquals(1, MathUtils.fibonacci(2));
        assertEquals(2, MathUtils.fibonacci(3));
        assertEquals(3, MathUtils.fibonacci(4));
        assertEquals(5, MathUtils.fibonacci(5));
        assertEquals(8, MathUtils.fibonacci(6));
        assertEquals(13, MathUtils.fibonacci(7));
        assertEquals(21, MathUtils.fibonacci(8));
    }

    @Test
    @DisplayName("fibonacci() should throw exception for negative position")
    void testFibonacciNegative() {
        assertThrows(IllegalArgumentException.class, () -> MathUtils.fibonacci(-1));
    }

    @Test
    @DisplayName("isPerfectSquare() should identify perfect squares correctly")
    void testIsPerfectSquare() {
        assertTrue(MathUtils.isPerfectSquare(0));
        assertTrue(MathUtils.isPerfectSquare(1));
        assertTrue(MathUtils.isPerfectSquare(4));
        assertTrue(MathUtils.isPerfectSquare(9));
        assertTrue(MathUtils.isPerfectSquare(16));
        assertTrue(MathUtils.isPerfectSquare(25));
        assertTrue(MathUtils.isPerfectSquare(100));
        assertFalse(MathUtils.isPerfectSquare(2));
        assertFalse(MathUtils.isPerfectSquare(3));
        assertFalse(MathUtils.isPerfectSquare(5));
        assertFalse(MathUtils.isPerfectSquare(-1));
        assertFalse(MathUtils.isPerfectSquare(-4));
    }
}
