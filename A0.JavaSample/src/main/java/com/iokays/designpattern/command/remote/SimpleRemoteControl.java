package com.iokays.designpattern.command.remote;


/**
 * 简单遥控器
 * 简单遥控器
 */
public class SimpleRemoteControl {

    /**
     * 命令插槽
     */
    private Command slot;

    public SimpleRemoteControl() {
    }

    /**
     * 设置命令", notes = "设置命令
     */
    public void setCommand(Command command) {
        slot = command;
    }

    /**
     * 按钮被按下", notes = "按钮被按下
     */
    public void buttonWasPressed() {
        slot.execute();
    }

}
