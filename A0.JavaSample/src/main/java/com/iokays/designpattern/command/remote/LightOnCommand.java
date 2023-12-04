package com.iokays.designpattern.command.remote;


/**
 * 打开电灯命令
 * 打开电灯命令
 */
public class LightOnCommand implements Command {

    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }

}
