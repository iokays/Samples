package com.iokays.designpattern.facade.hometheater;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

@ApiModel("投影仪")
public class Projector {

    @ApiModelProperty("描述")
    private String description;

    public Projector() {
        this.description = "Projector";
    }

    @ApiOperation("打开投影仪")
    public void on() {
        System.out.println("Projector on");
    }

    @ApiOperation("设置投影仪宽屏模式")
    public void wideScreenMode() {
        System.out.println("Projector in widescreen mode (16x9 aspect ratio)");
    }

    @ApiOperation("关闭投影仪")
    public void off() {
        System.out.println("Projector off");
    }
}
