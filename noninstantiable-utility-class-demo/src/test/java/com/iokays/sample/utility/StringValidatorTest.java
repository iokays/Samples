package com.iokays.sample.utility;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for StringValidator demonstrating noninstantiability.
 */
@DisplayName("StringValidator Tests")
class StringValidatorTest {

    @Test
    @DisplayName("Private constructor should throw AssertionError when accessed via reflection")
    void privateConstructorShouldThrowAssertionError() throws Exception {
        Constructor<StringValidator> constructor = StringValidator.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        InvocationTargetException exception = assertThrows(
            InvocationTargetException.class,
            constructor::newInstance
        );

        assertTrue(exception.getCause() instanceof AssertionError);
        assertTrue(exception.getCause().getMessage().contains("should not be instantiated"));
    }

    @Test
    @DisplayName("isValidEmail() should validate email addresses correctly")
    void testIsValidEmail() {
        assertTrue(StringValidator.isValidEmail("test@example.com"));
        assertTrue(StringValidator.isValidEmail("user.name@domain.org"));
        assertTrue(StringValidator.isValidEmail("user+tag@example.co.uk"));
        assertFalse(StringValidator.isValidEmail(null));
        assertFalse(StringValidator.isValidEmail(""));
        assertFalse(StringValidator.isValidEmail("invalid"));
        assertFalse(StringValidator.isValidEmail("invalid@"));
        assertFalse(StringValidator.isValidEmail("@invalid.com"));
        assertFalse(StringValidator.isValidEmail("invalid@.com"));
    }

    @Test
    @DisplayName("isValidPhone() should validate phone numbers correctly")
    void testIsValidPhone() {
        assertTrue(StringValidator.isValidPhone("1234567890"));
        assertTrue(StringValidator.isValidPhone("+12345678901"));
        assertTrue(StringValidator.isValidPhone("8613812345678"));
        assertFalse(StringValidator.isValidPhone(null));
        assertFalse(StringValidator.isValidPhone(""));
        assertFalse(StringValidator.isValidPhone("123"));
        assertFalse(StringValidator.isValidPhone("abcdefghij"));
    }

    @Test
    @DisplayName("isValidChinesePhone() should validate Chinese mobile numbers correctly")
    void testIsValidChinesePhone() {
        assertTrue(StringValidator.isValidChinesePhone("13812345678"));
        assertTrue(StringValidator.isValidChinesePhone("15912345678"));
        assertTrue(StringValidator.isValidChinesePhone("18812345678"));
        assertTrue(StringValidator.isValidChinesePhone("19912345678"));
        assertFalse(StringValidator.isValidChinesePhone(null));
        assertFalse(StringValidator.isValidChinesePhone(""));
        assertFalse(StringValidator.isValidChinesePhone("12345678901"));
        assertFalse(StringValidator.isValidChinesePhone("12812345678"));
        assertFalse(StringValidator.isValidChinesePhone("1381234567"));
        assertFalse(StringValidator.isValidChinesePhone("138123456789"));
    }

    @Test
    @DisplayName("isNumeric() should identify numeric strings correctly")
    void testIsNumeric() {
        assertTrue(StringValidator.isNumeric("123"));
        assertTrue(StringValidator.isNumeric("0"));
        assertTrue(StringValidator.isNumeric("999999"));
        assertFalse(StringValidator.isNumeric(null));
        assertFalse(StringValidator.isNumeric(""));
        assertFalse(StringValidator.isNumeric("123a"));
        assertFalse(StringValidator.isNumeric("12.3"));
        assertFalse(StringValidator.isNumeric("-123"));
    }

    @Test
    @DisplayName("isAlpha() should identify alphabetic strings correctly")
    void testIsAlpha() {
        assertTrue(StringValidator.isAlpha("abc"));
        assertTrue(StringValidator.isAlpha("ABC"));
        assertTrue(StringValidator.isAlpha("AbCdEf"));
        assertFalse(StringValidator.isAlpha(null));
        assertFalse(StringValidator.isAlpha(""));
        assertFalse(StringValidator.isAlpha("abc123"));
        assertFalse(StringValidator.isAlpha("abc def"));
        assertFalse(StringValidator.isAlpha("中文"));
    }

    @Test
    @DisplayName("isAlphanumeric() should identify alphanumeric strings correctly")
    void testIsAlphanumeric() {
        assertTrue(StringValidator.isAlphanumeric("abc123"));
        assertTrue(StringValidator.isAlphanumeric("ABC"));
        assertTrue(StringValidator.isAlphanumeric("123"));
        assertTrue(StringValidator.isAlphanumeric("aBc123"));
        assertFalse(StringValidator.isAlphanumeric(null));
        assertFalse(StringValidator.isAlphanumeric(""));
        assertFalse(StringValidator.isAlphanumeric("abc 123"));
        assertFalse(StringValidator.isAlphanumeric("abc-123"));
    }

    @Test
    @DisplayName("isLengthInRange() should check string length correctly")
    void testIsLengthInRange() {
        assertTrue(StringValidator.isLengthInRange("hello", 3, 10));
        assertTrue(StringValidator.isLengthInRange("abc", 3, 10));
        assertTrue(StringValidator.isLengthInRange("abcdefghij", 3, 10));
        assertFalse(StringValidator.isLengthInRange("ab", 3, 10));
        assertFalse(StringValidator.isLengthInRange("abcdefghijk", 3, 10));
        assertTrue(StringValidator.isLengthInRange(null, 0, 10));
        assertFalse(StringValidator.isLengthInRange(null, 1, 10));
    }

    @Test
    @DisplayName("containsChinese() should detect Chinese characters correctly")
    void testContainsChinese() {
        assertTrue(StringValidator.containsChinese("中文"));
        assertTrue(StringValidator.containsChinese("Hello世界"));
        assertTrue(StringValidator.containsChinese("测试test"));
        assertFalse(StringValidator.containsChinese(null));
        assertFalse(StringValidator.containsChinese(""));
        assertFalse(StringValidator.containsChinese("Hello World"));
        assertFalse(StringValidator.containsChinese("12345"));
    }
}
