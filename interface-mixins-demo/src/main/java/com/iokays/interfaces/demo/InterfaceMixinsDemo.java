package com.iokays.interfaces.demo;

import java.util.List;
import java.util.Set;

public final class InterfaceMixinsDemo {
    public static void main(String[] args) {
        KnowledgeCard knowledgeCard = new KnowledgeCard("doc-7", Set.of("java", "design"), 6);
        OpsReminder opsReminder = new OpsReminder(23, Set.of("ops", "urgent"), 9);
        ReleaseTagView releaseTagView = new ReleaseTagView(Set.of("release", "ready"));

        List<Taggable> taggables = List.of(knowledgeCard, opsReminder);

        System.out.println("[mixins]");
        System.out.println("knowledgeCard.hasTag(java) = " + knowledgeCard.hasTag("java"));
        System.out.println("opsReminder.isUrgent = " + opsReminder.isUrgent());
        System.out.println("sharedContractCount = " + taggables.stream().filter(t -> t.hasTag("java") || t.hasTag("ops")).count());
        System.out.println();

        System.out.println("[skeletal-implementation]");
        System.out.println("view = " + releaseTagView);
        System.out.println("hasTag(release) = " + releaseTagView.hasTag("release"));
        System.out.println();

        System.out.println("[abstract-class-limit]");
        System.out.println("knowledgeCardBase = " + knowledgeCard.getClass().getSuperclass().getSimpleName());
        System.out.println("opsReminderBase = " + opsReminder.getClass().getSuperclass().getSimpleName());
        System.out.println("sameAbstractParentPossible = false");
    }
}
