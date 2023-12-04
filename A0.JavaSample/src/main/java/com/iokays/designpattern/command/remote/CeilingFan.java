package com.iokays.designpattern.command.remote;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "吊扇", description = "吊扇")
public class CeilingFan {

    @ApiModelProperty("高速")
    public static final int HIGH = 3;

    @ApiModelProperty("中速")
    public static final int MEDIUM = 2;

    @ApiModelProperty("低速")
    public static final int LOW = 1;

    @ApiModelProperty("关闭")
    public static final int OFF = 0;

    private String location;

    private int speed;

    public CeilingFan(String location) {
        this.location = location;
        speed = OFF;
    }

    public void high() {
        speed = HIGH;
        System.out.println(location + " ceiling fan is on high");
    }

    public void medium() {
        speed = MEDIUM;
        System.out.println(location + " ceiling fan is on medium");
    }

    public void low() {
        speed = LOW;
        System.out.println(location + " ceiling fan is on low");
    }

    public void off() {
        speed = OFF;
        System.out.println(location + " ceiling fan is off");
    }

    public int getSpeed() {
        return speed;
    }

}
