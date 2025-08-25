package com.iokays.common.core.error;

public abstract class ApplicationServiceException extends AbstractCommonException {

    protected ApplicationServiceException(String message) {
        super(message);
    }
}
