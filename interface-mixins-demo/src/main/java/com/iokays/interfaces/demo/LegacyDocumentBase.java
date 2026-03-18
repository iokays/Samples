package com.iokays.interfaces.demo;

public abstract class LegacyDocumentBase {
    private final String documentId;

    protected LegacyDocumentBase(String documentId) {
        this.documentId = documentId;
    }

    public String documentId() {
        return documentId;
    }
}
