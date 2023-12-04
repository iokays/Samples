package com.iokays.designpattern.facade.hometheater;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

@ApiModel("调谐器")
public class Tuner {

    @ApiModelProperty("描述")
    private String description;

    public Tuner() {
        this.description = "Tuner";
    }

    @ApiOperation("打开调谐器")
    public void on() {
        System.out.println("Tuner on");
    }

    @ApiOperation("关闭调谐器")
    public void off() {
        System.out.println("Tuner off");
    }

    @ApiOperation("设置频率")
    public void setFrequency(double frequency) {
        System.out.println("Tuner setting frequency to " + frequency);
    }

}
