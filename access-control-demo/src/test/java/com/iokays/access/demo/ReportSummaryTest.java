package com.iokays.access.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReportSummaryTest {

    @Test
    void valueObjectExposesBehaviorThroughMethodsInsteadOfPublicFields() {
        ReportSummary summary = new ReportSummary("Q1", 12.5);

        assertEquals("Q1", summary.title());
        assertEquals(12.5, summary.revenue());
    }
}
