package com.iokays.accessors.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ImmutableTimePointTest {

    @Test
    void immutablePublicFieldsCanBeValidatedAtConstructionTime() {
        ImmutableTimePoint point = new ImmutableTimePoint(9, 30);

        assertEquals(9, point.hour);
        assertEquals(30, point.minute);
    }
}
