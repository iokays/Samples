package com.iokays.common.core.error;

public class ApplicationServiceUnKnowException extends ApplicationServiceException {

    public ApplicationServiceUnKnowException(String message) {
        super(message);
    }

    public ApplicationServiceUnKnowException(String message, Throwable e) {
        super(message);
    }
}
