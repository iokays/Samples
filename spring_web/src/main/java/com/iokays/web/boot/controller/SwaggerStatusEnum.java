package com.iokays.web.boot.controller;

//@SwaggerDisplayEnum
public enum SwaggerStatusEnum {

    SUCCESS(1,"成功"), FAIL(2, "失败");

    private Integer code;
    private String desc;

    SwaggerStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return code + ":" + desc;
    }
}
