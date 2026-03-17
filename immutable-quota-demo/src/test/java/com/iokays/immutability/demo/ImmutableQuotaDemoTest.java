package com.iokays.immutability.demo;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ImmutableQuotaDemoTest {
    @Test
    void mainPrintsExpectedSections() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(output, true, StandardCharsets.UTF_8));
        try {
            ImmutableQuotaDemo.main(new String[0]);
        } finally {
            System.setOut(original);
        }

        String text = output.toString(StandardCharsets.UTF_8);
        assertTrue(text.contains("[mutable]"));
        assertTrue(text.contains("[immutable]"));
        assertTrue(text.contains("[mutable-companion]"));
    }
}
