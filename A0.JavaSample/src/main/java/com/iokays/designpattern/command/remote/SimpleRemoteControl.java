package com.iokays.designpattern.command.remote;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

@ApiModel(value = "简单遥控器", description = "简单遥控器")
public class SimpleRemoteControl {

    @ApiModelProperty("命令插槽")
    private Command slot;

    public SimpleRemoteControl() {
    }

    @ApiOperation(value = "设置命令", notes = "设置命令")
    public void setCommand(Command command) {
        slot = command;
    }

    @ApiOperation(value = "按钮被按下", notes = "按钮被按下")
    public void buttonWasPressed() {
        slot.execute();
    }

}
