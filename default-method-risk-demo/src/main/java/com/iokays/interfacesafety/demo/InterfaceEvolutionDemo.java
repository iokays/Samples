package com.iokays.interfacesafety.demo;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public final class InterfaceEvolutionDemo {
    public static void main(String[] args) {
        ArraySequence<String> plain = new ArraySequence<>(List.of("draft", "ready", "archived"));
        plain.removeIf("draft"::equals);

        AtomicBoolean auditLocked = new AtomicBoolean(true);
        AuditLockedSequence<String> wrapped = new AuditLockedSequence<>(
                new ArraySequence<>(List.of("draft", "ready", "archived")),
                auditLocked
        );

        String inheritedFailure;
        try {
            wrapped.removeIf("draft"::equals);
            inheritedFailure = "none";
        } catch (IllegalStateException ex) {
            inheritedFailure = ex.getMessage();
        }

        boolean safeRemoval = wrapped.removeIfWithAuditLock("draft"::equals);

        System.out.println("[plain-default-method]");
        System.out.println("size = " + plain.size());
        System.out.println("contents = " + plain);
        System.out.println();

        System.out.println("[inherited-risk]");
        System.out.println("failure = " + inheritedFailure);
        System.out.println("contents = " + wrapped);
        System.out.println();

        System.out.println("[careful-override]");
        System.out.println("safeRemoval = " + safeRemoval);
        System.out.println("size = " + wrapped.size());
        System.out.println("contents = " + wrapped);
    }
}
