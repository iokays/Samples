package com.iokays.inheritance.demo;

public final class CountingTemplate extends SafeTemplate {
    private final String headline;
    private int renderCalls;

    public CountingTemplate(String headline) {
        this.headline = headline;
    }

    @Override
    protected String render() {
        renderCalls++;
        return headline;
    }

    public int renderCalls() {
        return renderCalls;
    }
}
