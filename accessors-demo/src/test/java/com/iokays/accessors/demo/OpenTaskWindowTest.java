package com.iokays.accessors.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OpenTaskWindowTest {

    @Test
    void constructorValidatesInitialState() {
        assertThrows(IllegalArgumentException.class, () -> new OpenTaskWindow(18, 9));
    }

    @Test
    void publicFieldsLetCallersBreakInvariantsAfterConstruction() {
        OpenTaskWindow window = new OpenTaskWindow(9, 18);
        window.endHour = 7;

        assertFalse(window.isValid());
        assertEquals(-120, window.durationMinutes());
    }
}
