package com.iokays.inheritance.demo;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FinalTemplateSnapshotTest {
    @Test
    void finalClassProhibitsInheritance() {
        assertTrue(Modifier.isFinal(FinalTemplateSnapshot.class.getModifiers()));
    }

    @Test
    void staticFactoryBuildsSnapshot() {
        FinalTemplateSnapshot snapshot = FinalTemplateSnapshot.of("READY");

        assertEquals("READY", snapshot.headline());
    }
}
