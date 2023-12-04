package com.iokays.designpattern.facade.hometheater;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

@ApiModel("灯光")
public class TheaterLights {

    @ApiModelProperty("描述")
    private String description;

    public TheaterLights() {
        this.description = "Theater Ceiling Lights";
    }

    @ApiOperation("打开灯光")
    public void dim(int i) {
        System.out.println("Theater Ceiling Lights dimming to 10%");
    }

    @ApiOperation("关闭灯光")
    public void on() {
        System.out.println("Theater Ceiling Lights on");
    }
}
