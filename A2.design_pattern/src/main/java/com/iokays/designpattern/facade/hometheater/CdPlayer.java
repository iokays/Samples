package com.iokays.designpattern.facade.hometheater;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

@ApiModel("CD播放器")
public class CdPlayer {


    @ApiModelProperty("描述")
    private String description;

    public CdPlayer() {
        this.description = "CD Player";
    }

    @ApiOperation("打开CD播放器")
    public void on() {
        System.out.println("CdPlayer on");
    }

    @ApiOperation("关闭CD播放器")
    public void off() {
        System.out.println("CdPlayer off");
    }

    @ApiOperation("播放CD")
    public void eject() {
        System.out.println("CdPlayer eject");
    }

    @ApiOperation("停止播放CD")
    public void play(String cdTitle) {
        System.out.println("CdPlayer playing " + cdTitle);
    }

}
