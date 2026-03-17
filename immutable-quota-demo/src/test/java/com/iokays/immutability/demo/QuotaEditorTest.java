package com.iokays.immutability.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuotaEditorTest {
    @Test
    void mutableCompanionHelpsBatchEditsThenBuildImmutableResult() {
        QuotaEditor editor = new QuotaEditor(10, 4);

        QuotaSnapshot snapshot = editor.expand(2).reserve(3).build();

        assertEquals(12, snapshot.limit());
        assertEquals(7, snapshot.used());
        assertEquals(5, snapshot.remaining());
    }

    @Test
    void editorStillRejectsImpossibleState() {
        QuotaEditor editor = new QuotaEditor(10, 9);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> editor.reserve(2));

        assertEquals("used must be between 0 and limit", ex.getMessage());
    }
}
