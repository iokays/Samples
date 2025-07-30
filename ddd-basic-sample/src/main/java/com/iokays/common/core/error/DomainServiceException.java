package com.iokays.common.core.error;

public abstract class DomainServiceException extends AbstractCommonException {

    protected DomainServiceException(final String message) {
        super(message);
    }
}
