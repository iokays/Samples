package com.iokays.inheritance.demo;

public final class FinalTemplateSnapshot {
    private final String headline;

    private FinalTemplateSnapshot(String headline) {
        this.headline = headline;
    }

    public static FinalTemplateSnapshot of(String headline) {
        return new FinalTemplateSnapshot(headline);
    }

    public String headline() {
        return headline;
    }

    @Override
    public String toString() {
        return "FinalTemplateSnapshot[headline=" + headline + "]";
    }
}
