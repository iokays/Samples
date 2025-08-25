package com.iokays.core.mongodb;

import lombok.Data;

@Data
public class Account {

    private Type accountType;

    private Float balance;

    public static enum Type {
        SAVINGS;
    }

}
