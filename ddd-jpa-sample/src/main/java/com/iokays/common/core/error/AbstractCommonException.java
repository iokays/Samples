package com.iokays.common.core.error;

public abstract class AbstractCommonException extends RuntimeException {
    protected AbstractCommonException(final String message) {
        super(message);
    }
}
