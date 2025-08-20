package com.iokays.sample.jdk12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

public class SwitchTest {

    @Test
    void testSwitchOnLambda() {
        final Function<String, String> dayType = day ->
                switch (day) {
                    case "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" -> "Weekday";
                    case "Saturday", "Sunday" -> "Weekend";
                    default -> "Unknown";
                };

        Assertions.assertEquals("Weekend", dayType.apply("Saturday"));
        Assertions.assertEquals("Weekend", dayType.apply("Sunday"));
        Assertions.assertEquals("Weekday", dayType.apply("Monday"));
        Assertions.assertEquals("Weekday", dayType.apply("Thursday"));
        Assertions.assertEquals("Unknown", dayType.apply("X"));
    }

}
