package com.iokays.designpattern.command.remote;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

@ApiModel(value = "车库门")
public class GarageDoor {

    @ApiModelProperty(value = "位置")
    private String location;

    public GarageDoor(String location) {
        this.location = location;
    }

    @ApiOperation(value = "上升", notes = "上升")
    public void up() {
        System.out.println("GarageDoor is up");
    }

    @ApiOperation(value = "下降", notes = "下降")
    public void down() {
        System.out.println("GarageDoor is down");
    }

    @ApiOperation(value = "停止", notes = "停止")
    public void stop() {
        System.out.println("GarageDoor is stop");
    }

    @ApiOperation(value = "打开灯", notes = "打开")
    public void lightOn() {
        System.out.println("GarageDoor light is on");
    }

    @ApiOperation(value = "关闭灯", notes = "关闭")
    public void lightOff() {
        System.out.println("GarageDoor light is off");
    }

}
