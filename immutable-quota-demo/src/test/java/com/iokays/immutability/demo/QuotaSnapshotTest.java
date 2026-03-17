package com.iokays.immutability.demo;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class QuotaSnapshotTest {
    @Test
    void reserveReturnsNewInstanceAndKeepsOriginalUntouched() {
        QuotaSnapshot original = QuotaSnapshot.of(10, 4);

        QuotaSnapshot updated = original.reserve(3);

        assertNotSame(original, updated);
        assertEquals(4, original.used());
        assertEquals(7, updated.used());
    }

    @Test
    void invalidUpdateFailsAtomically() {
        QuotaSnapshot original = QuotaSnapshot.of(10, 9);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> original.reserve(2));

        assertEquals("used must be between 0 and limit", ex.getMessage());
        assertEquals(9, original.used());
    }

    @Test
    void immutableValueWorksReliablyAsSetElement() {
        Set<QuotaSnapshot> snapshots = new HashSet<>();
        QuotaSnapshot snapshot = QuotaSnapshot.of(10, 4);
        snapshots.add(snapshot);

        assertTrue(snapshots.contains(QuotaSnapshot.of(10, 4)));
    }
}
