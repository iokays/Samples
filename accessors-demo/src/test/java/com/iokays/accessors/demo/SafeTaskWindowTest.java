package com.iokays.accessors.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SafeTaskWindowTest {

    @Test
    void settersRejectInvalidRangeUpdates() {
        SafeTaskWindow window = new SafeTaskWindow(9, 18);

        assertThrows(IllegalArgumentException.class, () -> window.setEndHour(7));
        assertEquals(540, window.durationMinutes());
    }

    @Test
    void settersAllowValidUpdates() {
        SafeTaskWindow window = new SafeTaskWindow(9, 18);
        window.setStartHour(10);
        window.setEndHour(19);

        assertEquals(10, window.getStartHour());
        assertEquals(19, window.getEndHour());
    }
}
