package com.iokays.analysispattern.accountability;

import com.google.common.base.Preconditions;

public class Division extends Organization {

    public Division(String name, Organization parent) {
        super(name, parent);
    }

    @Override
    protected void assertValidParent(Organization parent) {
        Preconditions.checkState(parent instanceof Company);
        super.assertValidParent(parent);
    }
}
