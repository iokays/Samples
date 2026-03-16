package com.iokays.clone.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeepCloneReportStackTest {

    @Test
    void deepCloneCopiesInternalArraySoOriginalStaysValid() {
        DeepCloneReportStack original = new DeepCloneReportStack();
        original.push("summary");
        original.push("cashflow");

        DeepCloneReportStack cloned = original.clone();

        assertEquals("cashflow", cloned.pop());
        assertEquals("cashflow", original.pop());
    }

    @Test
    void clonePreservesIndependentSizesAfterSeparateMutation() {
        DeepCloneReportStack original = new DeepCloneReportStack();
        original.push("summary");
        original.push("cashflow");

        DeepCloneReportStack cloned = original.clone();
        cloned.pop();

        assertEquals(2, original.size());
        assertEquals(1, cloned.size());
    }

    @Test
    void cloneReturnsConcreteTypeWithoutClientCast() {
        DeepCloneReportStack original = new DeepCloneReportStack();

        DeepCloneReportStack cloned = original.clone();

        assertEquals(DeepCloneReportStack.class, cloned.getClass());
    }
}
