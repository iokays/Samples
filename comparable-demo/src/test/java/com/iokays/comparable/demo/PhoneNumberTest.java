package com.iokays.comparable.demo;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PhoneNumberTest {

    @Test
    void compareToSortsByAreaCodeThenPrefixThenLineNumber() {
        List<PhoneNumber> numbers = new java.util.ArrayList<>(List.of(
                new PhoneNumber(707, 867, 5309),
                new PhoneNumber(10, 123, 4567),
                new PhoneNumber(415, 222, 1000)
        ));

        numbers.sort(null);

        assertEquals(List.of(
                new PhoneNumber(10, 123, 4567),
                new PhoneNumber(415, 222, 1000),
                new PhoneNumber(707, 867, 5309)
        ), numbers);
    }

    @Test
    void compareToConsistentWithEqualsForPhoneNumber() {
        PhoneNumber a = new PhoneNumber(707, 867, 5309);
        PhoneNumber b = new PhoneNumber(707, 867, 5309);

        assertEquals(0, a.compareTo(b));
        assertEquals(a, b);
    }

    @Test
    void treeSetUsesNaturalOrderingToRemoveLogicalDuplicates() {
        TreeSet<PhoneNumber> numbers = new TreeSet<>();
        numbers.add(new PhoneNumber(707, 867, 5309));
        numbers.add(new PhoneNumber(707, 867, 5309));

        assertEquals(1, numbers.size());
    }

    @Test
    void compareToRejectsNull() {
        PhoneNumber a = new PhoneNumber(707, 867, 5309);

        assertThrows(NullPointerException.class, () -> a.compareTo(null));
    }
}
