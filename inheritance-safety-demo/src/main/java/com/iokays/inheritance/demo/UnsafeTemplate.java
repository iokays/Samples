package com.iokays.inheritance.demo;

public class UnsafeTemplate {
    private final String preview;

    protected UnsafeTemplate() {
        this.preview = render();
    }

    protected String render() {
        return "base";
    }

    public String preview() {
        return preview;
    }
}
