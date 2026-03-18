package com.iokays.inheritance.demo;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;

class InheritanceSafetyDemoTest {
    @Test
    void mainPrintsAllSections() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(output, true, StandardCharsets.UTF_8));
        try {
            InheritanceSafetyDemo.main(new String[0]);
        } finally {
            System.setOut(original);
        }

        String text = output.toString(StandardCharsets.UTF_8);
        assertTrue(text.contains("[broken-constructor]"));
        assertTrue(text.contains("[designed-for-inheritance]"));
        assertTrue(text.contains("[prohibit-inheritance]"));
    }
}
