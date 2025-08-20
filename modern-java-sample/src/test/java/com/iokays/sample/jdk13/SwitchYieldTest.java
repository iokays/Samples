package com.iokays.sample.jdk13;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;

public class SwitchYieldTest {

    @Test
    void testSwitchYieldOnLambda() {
        final BiFunction<String, Boolean, Integer> daysInMonth = (month, isLeapYear) ->
                switch (month) {
                    case "January", "March", "May", "July", "August", "October", "December" -> 31;
                    case "April", "June", "September", "November" -> 30;
                    case "February" -> {
                        if (isLeapYear) yield 29;  // JDK 13+ 可用 yield
                        else yield 28;
                    }
                    default -> throw new IllegalArgumentException("Invalid month");
                };

        Assertions.assertEquals(31, daysInMonth.apply("January", false));
        Assertions.assertEquals(31, daysInMonth.apply("March", false));
        Assertions.assertEquals(30, daysInMonth.apply("April", false));
        Assertions.assertEquals(29, daysInMonth.apply("February", true));
        Assertions.assertEquals(28, daysInMonth.apply("February", false));

    }

}
