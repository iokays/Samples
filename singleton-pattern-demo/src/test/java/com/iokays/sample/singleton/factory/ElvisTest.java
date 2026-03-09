package com.iokays.sample.singleton.factory;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ElvisTest {
    @Test
    void testSingletonInstance() {
        Elvis instance1 = Elvis.getInstance();
        Elvis instance2 = Elvis.getInstance();
        assertSame(instance1, instance2);
    }

    @Test
    void testSingletonBehavior() {
        Elvis elvis = Elvis.getInstance();
        assertNotNull(elvis);
        assertEquals("Elvis{singleton instance}", elvis.toString());
    }

    @Test
    void testLeaveTheBuilding() {
        Elvis elvis = Elvis.getInstance();
        assertDoesNotThrow(() -> elvis.leaveTheBuilding());
    }

    @Test
    void testMultipleCallsReturnSameInstance() {
        for (int i = 0; i < 100; i++) {
            assertSame(Elvis.getInstance(), Elvis.getInstance());
        }
    }
}
