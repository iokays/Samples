package com.iokays.designpattern.facade.hometheater;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

@ApiModel("屏幕")
public class Screen {


    @ApiModelProperty("描述")
    private String description;

    public Screen() {
        this.description = "Screen";
    }

    @ApiOperation("打开屏幕")
    public void up() {
        System.out.println("Screen going up");
    }

    @ApiOperation("关闭屏幕")
    public void down() {
        System.out.println("Screen going down");
    }

}
