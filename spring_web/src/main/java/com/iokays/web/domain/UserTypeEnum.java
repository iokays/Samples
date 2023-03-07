package com.iokays.web.domain;

public enum UserTypeEnum implements BaseEnum {
    DISABLE, ENABLE;

    public static void main(String[] args) {
        System.out.println(DISABLE.toMap());
    }
}


