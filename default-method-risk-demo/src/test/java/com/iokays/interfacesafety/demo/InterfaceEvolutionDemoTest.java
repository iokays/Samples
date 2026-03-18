package com.iokays.interfacesafety.demo;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;

class InterfaceEvolutionDemoTest {
    @Test
    void mainPrintsAllSections() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(output, true, StandardCharsets.UTF_8));
        try {
            InterfaceEvolutionDemo.main(new String[0]);
        } finally {
            System.setOut(original);
        }

        String text = output.toString(StandardCharsets.UTF_8);
        assertTrue(text.contains("[plain-default-method]"));
        assertTrue(text.contains("[inherited-risk]"));
        assertTrue(text.contains("[careful-override]"));
    }
}
