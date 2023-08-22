package com.iokays.analysispattern.base;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

public class NamedObject implements Serializable {

    private String name = "no name";

    public NamedObject(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    protected void assertNonNull(Object arg) {
        if (arg == null) {
            throw new NullPointerException();
        }
    }

    protected void assertNonNull(Object arg, String message) {
        if (arg == null) {
            throw new NullPointerException(message);
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
