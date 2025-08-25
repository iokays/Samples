package com.iokays.common.mongodb;

import org.apache.commons.lang3.Validate;

import java.io.Serial;
import java.io.Serializable;

/**
 * 业务主键
 */
public abstract class AbstractCode implements Identity, Comparable<AbstractCode>, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;

    protected AbstractCode(String aCode) {
        this();
        this.setCode(aCode);
    }

    protected AbstractCode() {
        super();
    }

    @Override
    public String id() {
        return this.id;
    }

    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }

        if (anObject instanceof AbstractCode other) {
            return this.id().equals(other.id());
        }

        return false;
    }

    @Override
    public int hashCode() {
        return +(this.hashOddValue() * this.hashPrimeValue()) + this.id().hashCode();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [id=" + id + "]";
    }

    protected int hashOddValue() {
        return this.getClass().hashCode();
    }

    protected int hashPrimeValue() {
        return 263;
    }

    protected void validateCode(String aCode) {
        // implemented by subclasses for validation.
        // throws a runtime exception if invalid.
    }

    private void setCode(String aCode) {
        Validate.notNull(aCode, "The basic identity is required.");
        this.validateCode(aCode);
        this.id = aCode;
    }

    @Override
    public int compareTo(AbstractCode o) {
        return this.id().compareTo(o.id());
    }

}