package com.iokays.designpattern.command.remote;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "关闭电灯命令", description = "关闭电灯命令")
public class LightOffCommand implements Command {

    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }

    @Override
    public void undo() {
        light.on();
    }

}

