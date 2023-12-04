package com.iokays.designpattern.facade.hometheater;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

@ApiModel("爆米花机")
public class PopcornPopper {

    @ApiModelProperty("描述")
    private String description;

    public PopcornPopper() {
        this.description = "Popcorn Popper";
    }

    @ApiOperation("打开爆米花机")
    public void on() {
        System.out.println("PopcornPopper on");
    }

    @ApiOperation("关闭爆米花机")
    public void off() {
        System.out.println("PopcornPopper off");
    }

    @ApiOperation("开始爆米花")
    public void pop() {
        System.out.println("PopcornPopper popping popcorn!");
    }
}
