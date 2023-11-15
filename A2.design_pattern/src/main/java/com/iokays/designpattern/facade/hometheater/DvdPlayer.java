package com.iokays.designpattern.facade.hometheater;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

@ApiModel("DVD播放器")
public class DvdPlayer {

    @ApiModelProperty("描述")
    private String description;

    public DvdPlayer() {
        this.description = "DVD Player";
    }

    @ApiOperation("打开DVD播放器")
    public void on() {
        System.out.println("DvdPlayer on");
    }

    @ApiOperation("关闭DVD播放器")
    public void off() {
        System.out.println("DvdPlayer off");
    }

    @ApiOperation("播放DVD")
    public void eject() {
        System.out.println("DvdPlayer eject");
    }

    @ApiOperation("停止播放DVD")
    public void play(String movie) {
        System.out.println("DvdPlayer playing " + movie);
    }

    @ApiOperation("设置环绕声")
    public void stop() {
        System.out.println("DvdPlayer stop");
    }

}
