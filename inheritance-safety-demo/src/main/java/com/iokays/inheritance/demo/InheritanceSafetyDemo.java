package com.iokays.inheritance.demo;

import java.lang.reflect.Modifier;

public final class InheritanceSafetyDemo {
    public static void main(String[] args) {
        BrokenHeadlineTemplate broken = new BrokenHeadlineTemplate("READY");
        CountingTemplate designed = new CountingTemplate("READY");
        FinalTemplateSnapshot snapshot = FinalTemplateSnapshot.of("READY");

        System.out.println("[broken-constructor]");
        System.out.println("preview = " + broken.preview());
        System.out.println("liveValue = " + broken.headline());
        System.out.println();

        System.out.println("[designed-for-inheritance]");
        System.out.println("preview = " + designed.preview());
        System.out.println("renderCallsBeforePreview = 0");
        System.out.println("renderCallsAfterPreview = " + designed.renderCalls());
        System.out.println();

        System.out.println("[prohibit-inheritance]");
        System.out.println("isFinal = " + Modifier.isFinal(FinalTemplateSnapshot.class.getModifiers()));
        System.out.println("snapshot = " + snapshot);
    }
}
