package com.iokays.clone.demo;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

class ReportPlanTest {

    @Test
    void copyConstructorProducesIndependentListState() {
        ReportPlan original = new ReportPlan(List.of("summary", "cashflow"));
        ReportPlan copied = new ReportPlan(original);

        copied.addSection("risk");

        assertEquals(List.of("summary", "cashflow"), original.sections());
        assertEquals(List.of("summary", "cashflow", "risk"), copied.sections());
    }

    @Test
    void copyFactoryAlsoProducesIndependentObject() {
        ReportPlan original = new ReportPlan(List.of("summary"));

        ReportPlan copied = ReportPlan.copyOf(original);

        assertNotSame(original, copied);
        assertEquals(original.sections(), copied.sections());
    }
}
