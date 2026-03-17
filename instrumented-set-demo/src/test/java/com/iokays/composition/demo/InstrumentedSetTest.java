package com.iokays.composition.demo;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InstrumentedSetTest {
    @Test
    void compositionCountsAddAllCorrectly() {
        InstrumentedSet<String> set = new InstrumentedSet<>(new HashSet<>());

        set.addAll(List.of("Snap", "Crackle", "Pop"));

        assertEquals(3, set.getAddCount());
        assertEquals(3, set.size());
    }

    @Test
    void wrapperCanDecorateDifferentSetImplementations() {
        InstrumentedSet<String> set = new InstrumentedSet<>(new TreeSet<>());

        set.addAll(List.of("Snap", "Crackle", "Pop"));

        assertEquals("Crackle", set.iterator().next());
        assertEquals(3, set.getAddCount());
    }

    @Test
    void forwardingStillSupportsNormalSetOperations() {
        InstrumentedSet<String> set = new InstrumentedSet<>(new HashSet<>());
        set.add("Snap");

        assertTrue(set.contains("Snap"));
        assertEquals(1, set.getAddCount());
    }
}
