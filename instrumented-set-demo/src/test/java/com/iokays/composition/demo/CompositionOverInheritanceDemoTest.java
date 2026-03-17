package com.iokays.composition.demo;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CompositionOverInheritanceDemoTest {
    @Test
    void mainPrintsAllSections() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(output, true, StandardCharsets.UTF_8));
        try {
            CompositionOverInheritanceDemo.main(new String[0]);
        } finally {
            System.setOut(original);
        }

        String text = output.toString(StandardCharsets.UTF_8);
        assertTrue(text.contains("[inheritance]"));
        assertTrue(text.contains("[composition]"));
        assertTrue(text.contains("[wrapper-flexibility]"));
    }
}
