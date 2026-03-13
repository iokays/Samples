package com.iokays.tostring.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PhoneNumberTest {

    @Test
    void toStringUsesDocumentedPhoneNumberFormat() {
        assertEquals("010-123-4567", new PhoneNumber(10, 123, 4567).toString());
    }

    @Test
    void parseSupportsRoundTripFromToString() {
        var original = new PhoneNumber(707, 867, 5309);

        assertEquals(original, PhoneNumber.parse(original.toString()));
    }

    @Test
    void parseRejectsUnexpectedFormat() {
        var error = assertThrows(IllegalArgumentException.class, () -> PhoneNumber.parse("10-123-4567"));

        assertTrue(error.getMessage().contains("Expected format"));
    }

    @Test
    void constructorRejectsOutOfRangeParts() {
        assertThrows(IllegalArgumentException.class, () -> new PhoneNumber(1000, 1, 1));
        assertThrows(IllegalArgumentException.class, () -> new PhoneNumber(1, 1000, 1));
        assertThrows(IllegalArgumentException.class, () -> new PhoneNumber(1, 1, 10000));
    }
}
