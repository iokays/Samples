package com.iokays.sample.utility;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for UtilityClass demonstrating noninstantiability.
 */
@DisplayName("UtilityClass Tests")
class UtilityClassTest {

    @Test
    @DisplayName("Private constructor should throw AssertionError when accessed via reflection")
    void privateConstructorShouldThrowAssertionError() throws Exception {
        Constructor<UtilityClass> constructor = UtilityClass.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        InvocationTargetException exception = assertThrows(
            InvocationTargetException.class,
            constructor::newInstance
        );

        assertTrue(exception.getCause() instanceof AssertionError);
        assertTrue(exception.getCause().getMessage().contains("should not be instantiated"));
    }

    @Test
    @DisplayName("max() should return the larger of two integers")
    void testMax() {
        assertEquals(5, UtilityClass.max(3, 5));
        assertEquals(5, UtilityClass.max(5, 3));
        assertEquals(-3, UtilityClass.max(-3, -5));
        assertEquals(0, UtilityClass.max(0, 0));
    }

    @Test
    @DisplayName("min() should return the smaller of two integers")
    void testMin() {
        assertEquals(3, UtilityClass.min(3, 5));
        assertEquals(3, UtilityClass.min(5, 3));
        assertEquals(-5, UtilityClass.min(-3, -5));
        assertEquals(0, UtilityClass.min(0, 0));
    }

    @Test
    @DisplayName("sum() should calculate the sum of all elements")
    void testSum() {
        assertEquals(15, UtilityClass.sum(new int[]{1, 2, 3, 4, 5}));
        assertEquals(0, UtilityClass.sum(new int[]{}));
        assertEquals(-15, UtilityClass.sum(new int[]{-1, -2, -3, -4, -5}));
        assertEquals(10, UtilityClass.sum(new int[]{10}));
    }

    @Test
    @DisplayName("sum() should throw IllegalArgumentException for null array")
    void testSumNullArray() {
        assertThrows(IllegalArgumentException.class, () -> UtilityClass.sum(null));
    }

    @Test
    @DisplayName("average() should calculate the average correctly")
    void testAverage() {
        assertEquals(3.0, UtilityClass.average(new int[]{1, 2, 3, 4, 5}), 0.001);
        assertEquals(2.5, UtilityClass.average(new int[]{1, 2, 3, 4}), 0.001);
        assertEquals(5.0, UtilityClass.average(new int[]{5}), 0.001);
    }

    @Test
    @DisplayName("average() should throw IllegalArgumentException for null or empty array")
    void testAverageInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> UtilityClass.average(null));
        assertThrows(IllegalArgumentException.class, () -> UtilityClass.average(new int[]{}));
    }

    @Test
    @DisplayName("isNullOrEmpty() should correctly identify null or empty strings")
    void testIsNullOrEmpty() {
        assertTrue(UtilityClass.isNullOrEmpty(null));
        assertTrue(UtilityClass.isNullOrEmpty(""));
        assertFalse(UtilityClass.isNullOrEmpty("hello"));
        assertFalse(UtilityClass.isNullOrEmpty(" "));
    }

    @Test
    @DisplayName("defaultIfNull() should return default value for null input")
    void testDefaultIfNull() {
        assertEquals("hello", UtilityClass.defaultIfNull("hello", "default"));
        assertEquals("default", UtilityClass.defaultIfNull(null, "default"));
        assertEquals("", UtilityClass.defaultIfNull("", "default"));
    }

    @Test
    @DisplayName("reverse() should reverse the string correctly")
    void testReverse() {
        assertEquals("cba", UtilityClass.reverse("abc"));
        assertEquals("", UtilityClass.reverse(""));
        assertNull(UtilityClass.reverse(null));
        assertEquals("54321", UtilityClass.reverse("12345"));
    }

    @Test
    @DisplayName("clamp() should constrain value within range")
    void testClamp() {
        assertEquals(5, UtilityClass.clamp(3, 5, 10));
        assertEquals(10, UtilityClass.clamp(15, 5, 10));
        assertEquals(7, UtilityClass.clamp(7, 5, 10));
        assertEquals(0, UtilityClass.clamp(-5, 0, 100));
    }

    @Test
    @DisplayName("Static constants should be accessible")
    void testStaticConstants() {
        assertEquals(3.141592653589793, UtilityClass.PI, 0.0000001);
        assertEquals(2.718281828459045, UtilityClass.E, 0.0000001);
    }
}
