package com.iokays.designpattern.command.remote;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;

import java.io.Serializable;

@ApiModel(value = "电灯", description = "电灯")
public class Light implements Serializable {

    private String location;

    public Light() {
        this("Light");
    }

    public Light(String location) {
        this.location = location;
    }

    @ApiOperation(value = "打开电灯", notes = "打开电灯")
    public void on() {
        System.out.println("Light is on");
    }

    @ApiOperation(value = "关闭电灯", notes = "关闭电灯")
    public void off() {
        System.out.println("Light is off");
    }
}
