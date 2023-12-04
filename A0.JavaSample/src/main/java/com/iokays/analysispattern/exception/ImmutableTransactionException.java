package com.iokays.analysispattern.exception;

public class ImmutableTransactionException extends RuntimeException {
        public ImmutableTransactionException(String message) {
            super(message);
        }
}
