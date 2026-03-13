package com.iokays.tostring.demo;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ToStringDemoTest {

    @Test
    void demoOutputShowsWhyDefaultObjectToStringIsUnhelpful() {
        var output = captureDemoOutput();

        assertTrue(output.contains("[default-object]"));
        assertTrue(output.contains("legacyPotion = com.iokays.tostring.demo.PotionWithoutToString@"));
    }

    @Test
    void demoOutputShowsRoundTripForDocumentedValueFormat() {
        var output = captureDemoOutput();

        assertTrue(output.contains("[formatted-value]"));
        assertTrue(output.contains("supportLine = 010-123-4567"));
        assertTrue(output.contains("roundTrip = true"));
    }

    @Test
    void demoOutputMakesCollectionDiagnosticsReadable() {
        var output = captureDemoOutput();

        assertTrue(output.contains("contacts = {"));
        assertTrue(output.contains("support=010-123-4567"));
        assertTrue(output.contains("potions = [[Potion #9: type=love, smell=turpentine, look=india ink]]"));
    }

    private static String captureDemoOutput() {
        var originalOut = System.out;
        var buffer = new ByteArrayOutputStream();
        try {
            System.setOut(new PrintStream(buffer, true, StandardCharsets.UTF_8));
            ToStringDemo.main(new String[0]);
        } finally {
            System.setOut(originalOut);
        }
        return buffer.toString(StandardCharsets.UTF_8);
    }
}
