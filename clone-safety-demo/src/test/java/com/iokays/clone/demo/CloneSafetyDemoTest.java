package com.iokays.clone.demo;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CloneSafetyDemoTest {

    @Test
    void demoOutputShowsBrokenCloneSymptoms() {
        String output = capture();

        assertTrue(output.contains("[broken-clone]"));
        assertTrue(output.contains("original.pop() = null"));
        assertTrue(output.contains("sharedMutableState = true"));
    }

    @Test
    void demoOutputShowsFixedCloneAndCopyConstructorAlternative() {
        String output = capture();

        assertTrue(output.contains("[fixed-clone]"));
        assertTrue(output.contains("independentState = true"));
        assertTrue(output.contains("[copy-constructor]"));
        assertTrue(output.contains("sameReference = false"));
    }

    private static String capture() {
        PrintStream original = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try {
            System.setOut(new PrintStream(buffer, true, StandardCharsets.UTF_8));
            CloneSafetyDemo.main(new String[0]);
        } finally {
            System.setOut(original);
        }
        return buffer.toString(StandardCharsets.UTF_8);
    }
}
