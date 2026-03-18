package com.iokays.inheritance.demo;

public abstract class SafeTemplate {
    protected SafeTemplate() {
    }

    public final String preview() {
        return render();
    }

    protected abstract String render();
}
