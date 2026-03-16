package com.iokays.clone.demo;

import java.util.List;

public final class CloneSafetyDemo {
    private CloneSafetyDemo() {
    }

    public static void main(String[] args) {
        runBrokenClone();
        System.out.println();
        runFixedClone();
        System.out.println();
        runCopyConstructorAlternative();
    }

    private static void runBrokenClone() {
        ShallowReportStack original = new ShallowReportStack();
        original.push("summary");
        original.push("cashflow");

        ShallowReportStack cloned = original.clone();
        String clonedPop = cloned.pop();
        String originalPop = original.pop();

        System.out.println("[broken-clone]");
        System.out.println("cloned.pop() = " + clonedPop);
        System.out.println("original.pop() = " + originalPop);
        System.out.println("sharedMutableState = " + (originalPop == null));
    }

    private static void runFixedClone() {
        DeepCloneReportStack original = new DeepCloneReportStack();
        original.push("summary");
        original.push("cashflow");

        DeepCloneReportStack cloned = original.clone();
        String clonedPop = cloned.pop();
        String originalPop = original.pop();

        System.out.println("[fixed-clone]");
        System.out.println("cloned.pop() = " + clonedPop);
        System.out.println("original.pop() = " + originalPop);
        System.out.println("independentState = " + "cashflow".equals(originalPop));
    }

    private static void runCopyConstructorAlternative() {
        ReportPlan original = new ReportPlan(List.of("summary", "cashflow"));
        ReportPlan copied = ReportPlan.copyOf(original);
        copied.addSection("risk");

        System.out.println("[copy-constructor]");
        System.out.println("original.sections = " + original.sections());
        System.out.println("copied.sections = " + copied.sections());
        System.out.println("sameReference = " + (original == copied));
    }
}
