package com.iokays.interfaces.demo;

import java.util.Set;

public final class ReleaseTagView extends AbstractTagView {
    public ReleaseTagView(Set<String> tags) {
        super(tags);
    }

    @Override
    protected String label() {
        return "ReleaseTagView";
    }
}
