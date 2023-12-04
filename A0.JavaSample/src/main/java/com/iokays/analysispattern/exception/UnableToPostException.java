package com.iokays.analysispattern.exception;

public class UnableToPostException extends RuntimeException {
    public UnableToPostException() {
        super("Unable to post transaction");
    }
}
