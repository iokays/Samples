package com.iokays.immutability.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MutableQuotaTest {
    @Test
    void settersCanBreakInvariantAfterConstruction() {
        MutableQuota quota = new MutableQuota(10, 4);

        quota.setLimit(3);

        assertFalse(quota.isValid());
        assertEquals(-1, quota.remaining());
    }

    @Test
    void constructorStillRejectsInvalidInitialState() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> new MutableQuota(3, 5));

        assertEquals("used must be between 0 and limit", ex.getMessage());
    }
}
