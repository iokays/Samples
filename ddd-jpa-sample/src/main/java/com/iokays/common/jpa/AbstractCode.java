package com.iokays.common.jpa;

import com.iokays.common.core.domain.Identity;
import jakarta.persistence.MappedSuperclass;
import org.apache.commons.lang3.Validate;

import java.io.Serial;
import java.io.Serializable;

/**
 * 业务主键
 */
@MappedSuperclass
public abstract class AbstractCode implements Identity, Comparable<AbstractCode>, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String code;

    protected AbstractCode(String code) {
        this();
        this.setCode(code);
    }

    protected AbstractCode() {
        super();
    }

    @Override
    public String code() {
        return this.code;
    }

    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }

        if (anObject instanceof AbstractCode other) {
            return this.code().equals(other.code());
        }

        return false;
    }

    @Override
    public int hashCode() {
        return +(this.hashOddValue() * this.hashPrimeValue()) + this.code().hashCode();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [id=" + code + "]";
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
        this.code = aCode;
    }

    @Override
    public int compareTo(AbstractCode o) {
        return this.code().compareTo(o.code());
    }

}