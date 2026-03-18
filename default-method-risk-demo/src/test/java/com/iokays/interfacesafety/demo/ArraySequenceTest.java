package com.iokays.interfacesafety.demo;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArraySequenceTest {
    @Test
    void defaultMethodWorksForPlainImplementation() {
        ArraySequence<String> sequence = new ArraySequence<>(List.of("draft", "ready", "archived"));

        boolean removed = sequence.removeIf("draft"::equals);

        assertEquals(true, removed);
        assertEquals(2, sequence.size());
        assertEquals("[ready, archived]", sequence.toString());
    }
}
