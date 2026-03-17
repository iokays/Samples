package com.iokays.access.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class InsecureDashboardConfigTest {

    @Test
    void publicStaticFinalArrayCanStillBeMutated() {
        String original = InsecureDashboardConfig.DEFAULT_SECTIONS[0];
        try {
            InsecureDashboardConfig.DEFAULT_SECTIONS[0] = "hacked";
            assertEquals("hacked", InsecureDashboardConfig.DEFAULT_SECTIONS[0]);
        } finally {
            InsecureDashboardConfig.DEFAULT_SECTIONS[0] = original;
        }
    }

    @Test
    void callersReceiveTheSameArrayReference() {
        assertSame(InsecureDashboardConfig.DEFAULT_SECTIONS, InsecureDashboardConfig.DEFAULT_SECTIONS);
    }
}
