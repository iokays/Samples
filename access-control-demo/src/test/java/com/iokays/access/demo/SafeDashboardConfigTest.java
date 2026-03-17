package com.iokays.access.demo;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SafeDashboardConfigTest {

    @Test
    void unmodifiableListRejectsMutation() {
        assertThrows(UnsupportedOperationException.class, () -> SafeDashboardConfig.DEFAULT_SECTIONS.set(0, "hacked"));
    }

    @Test
    void defensiveCopyPreventsCallersFromChangingOriginalArray() {
        String[] copy = SafeDashboardConfig.defaultSectionsCopy();
        copy[0] = "hacked";

        assertEquals(List.of("overview", "revenue", "risk"), SafeDashboardConfig.DEFAULT_SECTIONS);
    }

    @Test
    void defensiveCopyReturnsIndependentArrayInstances() {
        String[] first = SafeDashboardConfig.defaultSectionsCopy();
        String[] second = SafeDashboardConfig.defaultSectionsCopy();

        assertNotSame(first, second);
    }
}
