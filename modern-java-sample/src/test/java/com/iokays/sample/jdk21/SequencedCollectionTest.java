package com.iokays.sample.jdk21;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SequencedCollectionTest {

    @Test
    void testListOperations() {
        SequencedCollection<String> list = new ArrayList<>(List.of("B", "C"));

        list.addFirst("A");  // ["A", "B", "C"]
        list.addLast("D");   // ["A", "B", "C", "D"]

        assertEquals("A", list.getFirst());
        assertEquals("D", list.getLast());

        SequencedCollection<String> reversed = list.reversed();
        assertEquals(List.of("D", "C", "B", "A"), reversed.stream().toList());
    }

    @Test
    void testLinkedHashSet() {
        SequencedSet<String> set = new LinkedHashSet<>(Set.of("B", "C"));
        set.addFirst("A");  // ["A", "B", "C"]
        assertEquals("A", set.getFirst());
    }

}
