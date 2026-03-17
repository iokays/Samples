package com.iokays.accessors.demo;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AccessorsDemoTest {

    @Test
    void demoOutputShowsPublicFieldBreakageAndAccessorProtection() {
        String output = capture();

        assertTrue(output.contains("[public-fields]"));
        assertTrue(output.contains("isValid = false"));
        assertTrue(output.contains("durationMinutes = -120"));
        assertTrue(output.contains("[accessors]"));
        assertTrue(output.contains("blockedInvalidUpdate = true"));
        assertTrue(output.contains("durationMinutes = 540"));
    }

    private static String capture() {
        PrintStream original = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try {
            System.setOut(new PrintStream(buffer, true, StandardCharsets.UTF_8));
            AccessorsDemo.main(new String[0]);
        } finally {
            System.setOut(original);
        }
        return buffer.toString(StandardCharsets.UTF_8);
    }
}
