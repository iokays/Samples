package com.iokays.inheritance.demo;

public final class HeadlineTemplate extends SafeTemplate {
    private final String headline;

    public HeadlineTemplate(String headline) {
        this.headline = headline;
    }

    @Override
    protected String render() {
        return headline;
    }
}
