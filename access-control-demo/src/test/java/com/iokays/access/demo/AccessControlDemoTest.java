package com.iokays.access.demo;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AccessControlDemoTest {

    @Test
    void demoOutputShowsPublicArrayLeakAndSafeAlternatives() {
        String output = capture();

        assertTrue(output.contains("[public-array-leak]"));
        assertTrue(output.contains("mutatedSections = [hacked, revenue, risk]"));
        assertTrue(output.contains("[unmodifiable-list]"));
        assertTrue(output.contains("blockedMutation = true"));
        assertTrue(output.contains("[defensive-copy]"));
        assertTrue(output.contains("originalSections = [overview, revenue, risk]"));
    }

    private static String capture() {
        PrintStream original = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try {
            System.setOut(new PrintStream(buffer, true, StandardCharsets.UTF_8));
            AccessControlDemo.main(new String[0]);
        } finally {
            System.setOut(original);
            InsecureDashboardConfig.DEFAULT_SECTIONS[0] = "overview";
        }
        return buffer.toString(StandardCharsets.UTF_8);
    }
}
