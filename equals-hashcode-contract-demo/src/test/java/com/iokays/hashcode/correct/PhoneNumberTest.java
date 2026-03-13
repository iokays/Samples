package com.iokays.hashcode.correct;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Correct phone number")
class PhoneNumberTest {

    @Test
    @DisplayName("equal objects share hashCode")
    void equalObjectsShareHashCode() {
        PhoneNumber first = new PhoneNumber(707, 867, 5309);
        PhoneNumber second = new PhoneNumber(707, 867, 5309);

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    @DisplayName("HashMap lookup succeeds")
    void hashMapLookupWorks() {
        PhoneNumber stored = new PhoneNumber(707, 867, 5309);
        PhoneNumber probe = new PhoneNumber(707, 867, 5309);
        Map<PhoneNumber, String> contacts = new HashMap<>();
        contacts.put(stored, "Jenny");

        assertEquals("Jenny", contacts.get(probe));
    }

    @Test
    @DisplayName("HashSet contains succeeds")
    void hashSetContainsWorks() {
        PhoneNumber stored = new PhoneNumber(212, 555, 1000);
        PhoneNumber probe = new PhoneNumber(212, 555, 1000);
        Set<PhoneNumber> numbers = new HashSet<>();
        numbers.add(stored);

        assertTrue(numbers.contains(probe));
    }

    @Test
    @DisplayName("different values remain different")
    void differentValuesRemainDifferent() {
        PhoneNumber first = new PhoneNumber(212, 555, 1000);
        PhoneNumber second = new PhoneNumber(212, 555, 1001);

        assertNotEquals(first, second);
    }

    @Test
    @DisplayName("invalid values are rejected")
    void invalidValuesAreRejected() {
        assertThrows(IllegalArgumentException.class, () -> new PhoneNumber(-1, 555, 1000));
        assertThrows(IllegalArgumentException.class, () -> new PhoneNumber(212, 1000, 1000));
        assertThrows(IllegalArgumentException.class, () -> new PhoneNumber(212, 555, 10000));
    }
}
