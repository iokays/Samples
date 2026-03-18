package com.iokays.interfacesafety.demo;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AuditLockedSequenceTest {
    @Test
    void inheritedDefaultMethodCanBreakExistingInvariant() {
        AuditLockedSequence<String> sequence = new AuditLockedSequence<>(
                new ArraySequence<>(List.of("draft", "ready", "archived")),
                new AtomicBoolean(true)
        );

        IllegalStateException ex = assertThrows(IllegalStateException.class,
                () -> sequence.removeIf("draft"::equals));

        assertEquals("audit lock required before mutation", ex.getMessage());
    }

    @Test
    void carefulOverrideRestoresSafeBehavior() {
        AuditLockedSequence<String> sequence = new AuditLockedSequence<>(
                new ArraySequence<>(List.of("draft", "ready", "archived")),
                new AtomicBoolean(true)
        );

        boolean removed = sequence.removeIfWithAuditLock("draft"::equals);

        assertTrue(removed);
        assertEquals("[ready, archived]", sequence.toString());
    }
}
