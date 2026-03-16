package com.iokays.clone.demo;

import java.util.EmptyStackException;

public final class DeepCloneReportStack implements Cloneable {
    private String[] elements;
    private int size;

    public DeepCloneReportStack() {
        this.elements = new String[8];
    }

    public void push(String value) {
        ensureCapacity();
        elements[size++] = value;
    }

    public String pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        String result = elements[--size];
        elements[size] = null;
        return result;
    }

    public int size() {
        return size;
    }

    @Override
    public DeepCloneReportStack clone() {
        try {
            DeepCloneReportStack result = (DeepCloneReportStack) super.clone();
            result.elements = elements.clone();
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            String[] expanded = new String[elements.length * 2];
            System.arraycopy(elements, 0, expanded, 0, elements.length);
            elements = expanded;
        }
    }
}
