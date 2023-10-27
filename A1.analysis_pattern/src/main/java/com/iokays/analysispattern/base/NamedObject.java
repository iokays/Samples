package com.iokays.analysispattern.base;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Objects;

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
    public boolean equals(Object arg) {
        if (!(arg instanceof NamedObject)) {
            return false;
        }
        NamedObject other = (NamedObject) arg;
        return Objects.equals(name(), other.name());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name());
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
