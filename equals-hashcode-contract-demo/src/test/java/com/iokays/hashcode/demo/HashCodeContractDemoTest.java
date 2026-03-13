package com.iokays.hashcode.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Demo output")
class HashCodeContractDemoTest {

    @Test
    @DisplayName("main method prints broken and fixed scenarios")
    void mainMethodPrintsExpectedSections() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream original = System.out;
        try (PrintStream capture = new PrintStream(output, true, StandardCharsets.UTF_8)) {
            System.setOut(capture);
            HashCodeContractDemo.main(new String[0]);
        } finally {
            System.setOut(original);
        }

        String text = output.toString(StandardCharsets.UTF_8);
        assertTrue(text.contains("[broken-map]"));
        assertTrue(text.contains("map.get(probe) = null"));
        assertTrue(text.contains("[fixed-map]"));
        assertTrue(text.contains("map.get(probe) = Jenny"));
    }
}
