package com.iokays.interfaces.demo;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InterfaceMixinsTest {
    @Test
    void unrelatedClassHierarchiesCanShareInterfaces() {
        KnowledgeCard knowledgeCard = new KnowledgeCard("doc-7", Set.of("java", "design"), 6);
        OpsReminder opsReminder = new OpsReminder(23, Set.of("ops", "urgent"), 9);

        List<Taggable> taggables = List.of(knowledgeCard, opsReminder);

        assertTrue(knowledgeCard.hasTag("java"));
        assertTrue(opsReminder.isUrgent());
        assertEquals(2, taggables.size());
    }

    @Test
    void existingBaseClassDoesNotBlockNewInterface() {
        KnowledgeCard knowledgeCard = new KnowledgeCard("doc-7", Set.of("java"), 6);

        assertEquals("LegacyDocumentBase", knowledgeCard.getClass().getSuperclass().getSimpleName());
        assertTrue(knowledgeCard instanceof Taggable);
    }
}
