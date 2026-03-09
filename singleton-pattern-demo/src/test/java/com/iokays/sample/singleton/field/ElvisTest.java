package com.iokays.sample.singleton.field;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ElvisTest {
    @Test
    void testSingletonInstance() {
        Elvis instance1 = Elvis.INSTANCE;
        Elvis instance2 = Elvis.INSTANCE;
        assertSame(instance1, instance2);
    }

    @Test
    void testSingletonBehavior() {
        Elvis elvis = Elvis.INSTANCE;
        assertNotNull(elvis);
        assertEquals("Elvis{singleton instance}", elvis.toString());
    }

    @Test
    void testLeaveTheBuilding() {
        Elvis elvis = Elvis.INSTANCE;
        assertDoesNotThrow(() -> elvis.leaveTheBuilding());
    }
}
