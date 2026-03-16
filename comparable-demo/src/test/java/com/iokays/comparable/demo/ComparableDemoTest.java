package com.iokays.comparable.demo;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ComparableDemoTest {

    @Test
    void demoOutputShowsNaturalOrderingAndTreeSetBehavior() {
        String output = capture();

        assertTrue(output.contains("[natural-order]"));
        assertTrue(output.contains("sortedNumbers = [010-123-4567, 415-222-1000, 707-867-5309]"));
        assertTrue(output.contains("[treeset]"));
        assertTrue(output.contains("treeSetSize = 2"));
    }

    @Test
    void demoOutputShowsCompareToEqualsMismatchCase() {
        String output = capture();

        assertTrue(output.contains("[compareto-vs-equals]"));
        assertTrue(output.contains("a.equals(b) = false"));
        assertTrue(output.contains("a.compareTo(b) = 0"));
        assertTrue(output.contains("hashSetSize = 2"));
        assertTrue(output.contains("treeSetSize = 1"));
    }

    private static String capture() {
        PrintStream original = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try {
            System.setOut(new PrintStream(buffer, true, StandardCharsets.UTF_8));
            ComparableDemo.main(new String[0]);
        } finally {
            System.setOut(original);
        }
        return buffer.toString(StandardCharsets.UTF_8);
    }
}
