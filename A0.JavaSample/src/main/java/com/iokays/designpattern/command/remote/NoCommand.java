package com.iokays.designpattern.command.remote;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "空命令", description = "空命令")
public class NoCommand implements Command {

    @Override
    public void execute() {
    }

    @Override
    public void undo() {
    }

}
