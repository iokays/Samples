package com.iokays.hashcode.demo;

import com.iokays.hashcode.broken.PhoneNumberWithoutHashCode;
import com.iokays.hashcode.correct.PhoneNumber;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Demonstrate why overriding equals without hashCode breaks hash-based collections.
 */
public final class HashCodeContractDemo {
    private HashCodeContractDemo() {
    }

    public static void main(String[] args) {
        brokenHashMapLookup();
        System.out.println();
        brokenHashSetContains();
        System.out.println();
        fixedHashMapLookup();
    }

    private static void brokenHashMapLookup() {
        PhoneNumberWithoutHashCode stored = new PhoneNumberWithoutHashCode(707, 867, 5309);
        PhoneNumberWithoutHashCode probe = new PhoneNumberWithoutHashCode(707, 867, 5309);
        Map<PhoneNumberWithoutHashCode, String> contacts = new HashMap<>();
        contacts.put(stored, "Jenny");

        System.out.println("[broken-map]");
        System.out.println("stored.equals(probe) = " + stored.equals(probe));
        System.out.println("stored.hashCode() = " + stored.hashCode());
        System.out.println("probe.hashCode() = " + probe.hashCode());
        System.out.println("map.get(probe) = " + contacts.get(probe));
    }

    private static void brokenHashSetContains() {
        PhoneNumberWithoutHashCode stored = new PhoneNumberWithoutHashCode(212, 555, 1000);
        PhoneNumberWithoutHashCode probe = new PhoneNumberWithoutHashCode(212, 555, 1000);
        Set<PhoneNumberWithoutHashCode> numbers = new HashSet<>();
        numbers.add(stored);

        System.out.println("[broken-set]");
        System.out.println("stored.equals(probe) = " + stored.equals(probe));
        System.out.println("set.contains(probe) = " + numbers.contains(probe));
        System.out.println("set size = " + numbers.size());
    }

    private static void fixedHashMapLookup() {
        PhoneNumber stored = new PhoneNumber(707, 867, 5309);
        PhoneNumber probe = new PhoneNumber(707, 867, 5309);
        Map<PhoneNumber, String> contacts = new HashMap<>();
        contacts.put(stored, "Jenny");

        System.out.println("[fixed-map]");
        System.out.println("stored.equals(probe) = " + stored.equals(probe));
        System.out.println("stored.hashCode() = " + stored.hashCode());
        System.out.println("probe.hashCode() = " + probe.hashCode());
        System.out.println("map.get(probe) = " + contacts.get(probe));
    }
}
