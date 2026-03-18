package com.iokays.inheritance.demo;

public final class BrokenHeadlineTemplate extends UnsafeTemplate {
    private final String headline;

    public BrokenHeadlineTemplate(String headline) {
        this.headline = headline;
    }

    @Override
    protected String render() {
        return headline;
    }

    public String headline() {
        return headline;
    }
}
