package com.iokays.hashcode.broken;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Broken phone number")
class PhoneNumberWithoutHashCodeTest {

    @Test
    @DisplayName("equal objects still get different default hash codes")
    void equalObjectsDoNotShareHashCode() {
        PhoneNumberWithoutHashCode first = new PhoneNumberWithoutHashCode(707, 867, 5309);
        PhoneNumberWithoutHashCode second = new PhoneNumberWithoutHashCode(707, 867, 5309);

        assertTrue(first.equals(second));
        assertNotEquals(first.hashCode(), second.hashCode());
    }

    @Test
    @DisplayName("HashMap lookup misses logically equal key")
    void hashMapLookupFails() {
        PhoneNumberWithoutHashCode stored = new PhoneNumberWithoutHashCode(707, 867, 5309);
        PhoneNumberWithoutHashCode probe = new PhoneNumberWithoutHashCode(707, 867, 5309);
        Map<PhoneNumberWithoutHashCode, String> contacts = new HashMap<>();
        contacts.put(stored, "Jenny");

        assertNull(contacts.get(probe));
    }

    @Test
    @DisplayName("HashSet contains returns false for equal value")
    void hashSetContainsFails() {
        PhoneNumberWithoutHashCode stored = new PhoneNumberWithoutHashCode(212, 555, 1000);
        PhoneNumberWithoutHashCode probe = new PhoneNumberWithoutHashCode(212, 555, 1000);
        Set<PhoneNumberWithoutHashCode> numbers = new HashSet<>();
        numbers.add(stored);

        assertFalse(numbers.contains(probe));
    }
}
