package com.iokays.web;

import java.io.Serializable;

public class Refactor implements Serializable {

    private final String stylus;

    public Refactor(String stylus) {
        this.stylus = stylus;
    }

    public String getStylus() {
        return stylus;
    }

}
