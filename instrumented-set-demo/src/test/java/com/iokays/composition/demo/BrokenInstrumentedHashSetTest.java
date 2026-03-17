package com.iokays.composition.demo;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BrokenInstrumentedHashSetTest {
    @Test
    void addAllDoubleCountsBecauseHashSetSelfUsesAdd() {
        BrokenInstrumentedHashSet<String> set = new BrokenInstrumentedHashSet<>();

        set.addAll(List.of("Snap", "Crackle", "Pop"));

        assertEquals(6, set.getAddCount());
        assertEquals(3, set.size());
    }
}
