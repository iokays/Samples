package com.iokays.tostring.demo;

import java.util.Objects;

public final class PotionWithoutToString {
    private final int id;
    private final String type;
    private final String smell;
    private final String look;

    public PotionWithoutToString(int id, String type, String smell, String look) {
        this.id = id;
        this.type = Objects.requireNonNull(type, "type");
        this.smell = Objects.requireNonNull(smell, "smell");
        this.look = Objects.requireNonNull(look, "look");
    }

    public int id() {
        return id;
    }

    public String type() {
        return type;
    }

    public String smell() {
        return smell;
    }

    public String look() {
        return look;
    }
}
