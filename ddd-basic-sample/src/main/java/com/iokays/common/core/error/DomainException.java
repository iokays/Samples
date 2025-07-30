package com.iokays.common.core.error;

public abstract class DomainException extends AbstractCommonException {

    protected DomainException(final String message) {
        super(message);
    }

}
