package com.iokays.access.demo;

import java.util.Arrays;

public final class AccessControlDemo {
    private AccessControlDemo() {
    }

    public static void main(String[] args) {
        runPublicArrayLeak();
        System.out.println();
        runUnmodifiableListProtection();
        System.out.println();
        runDefensiveCopyProtection();
    }

    private static void runPublicArrayLeak() {
        String[] leaked = InsecureDashboardConfig.DEFAULT_SECTIONS;
        leaked[0] = "hacked";

        System.out.println("[public-array-leak]");
        System.out.println("mutatedSections = " + Arrays.toString(InsecureDashboardConfig.DEFAULT_SECTIONS));
        System.out.println("sharedReference = " + (leaked == InsecureDashboardConfig.DEFAULT_SECTIONS));
    }

    private static void runUnmodifiableListProtection() {
        boolean blocked = false;
        try {
            SafeDashboardConfig.DEFAULT_SECTIONS.set(0, "hacked");
        } catch (UnsupportedOperationException ex) {
            blocked = true;
        }

        System.out.println("[unmodifiable-list]");
        System.out.println("blockedMutation = " + blocked);
        System.out.println("sections = " + SafeDashboardConfig.DEFAULT_SECTIONS);
    }

    private static void runDefensiveCopyProtection() {
        String[] copy = SafeDashboardConfig.defaultSectionsCopy();
        copy[0] = "hacked";

        System.out.println("[defensive-copy]");
        System.out.println("mutatedCopy = " + Arrays.toString(copy));
        System.out.println("originalSections = " + SafeDashboardConfig.DEFAULT_SECTIONS);
        System.out.println("sameReference = " + (copy == SafeDashboardConfig.defaultSectionsCopy()));
    }
}
