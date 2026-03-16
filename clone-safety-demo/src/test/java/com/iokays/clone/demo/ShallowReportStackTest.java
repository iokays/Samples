package com.iokays.clone.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ShallowReportStackTest {

    @Test
    void shallowCloneSharesInternalArrayAndCorruptsOriginal() {
        ShallowReportStack original = new ShallowReportStack();
        original.push("summary");
        original.push("cashflow");

        ShallowReportStack cloned = original.clone();
        assertEquals("cashflow", cloned.pop());
        assertNull(original.pop());
    }

    @Test
    void cloneKeepsCopiedSizeValueEvenThoughStateIsShared() {
        ShallowReportStack original = new ShallowReportStack();
        original.push("summary");

        ShallowReportStack cloned = original.clone();

        assertEquals(1, original.size());
        assertEquals(1, cloned.size());
    }

    @Test
    void cloneIsDifferentObjectReference() {
        ShallowReportStack original = new ShallowReportStack();
        original.push("summary");

        ShallowReportStack cloned = original.clone();

        assertEquals(false, original == cloned);
    }
}
