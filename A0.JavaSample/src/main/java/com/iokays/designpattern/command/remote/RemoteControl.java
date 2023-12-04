package com.iokays.designpattern.command.remote;


/**
 * 遥控器
 * 遥控器
 */
public class RemoteControl {

    /**
     * 开启命令
     */
    Command[] onCommands;

    /**
     * 关闭命令
     */
    Command[] offCommands;

    Command undoCommand;

    public RemoteControl() {
        onCommands = new Command[7];
        offCommands = new Command[7];

        Command noCommand = new NoCommand();
        for (int i = 0; i < 7; i++) {
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
        undoCommand = noCommand;
    }

    /**
     * 设置命令", notes = "设置命令
     */
    public void setCommand(int slot, Command onCommand, Command offCommand) {
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }

    /**
     * 按下开启按钮", notes = "按下开启按钮
     */
    public void onButtonWasPushed(int slot) {
        onCommands[slot].execute();
        undoCommand = onCommands[slot];
    }

    /**
     * 按下关闭按钮", notes = "按下关闭按钮
     */
    public void offButtonWasPushed(int slot) {
        offCommands[slot].execute();
        undoCommand = offCommands[slot];
    }

    /**
     * 按下撤销按钮", notes = "按下撤销按钮
     */
    public void undoButtonWasPushed() {
        undoCommand.undo();
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\n------ Remote Control ------\n");
        for (int i = 0; i < onCommands.length; i++) {
            stringBuffer.append("[slot " + i + "] " + onCommands[i].getClass().getName() + "    " + offCommands[i].getClass().getName() + "\n");
        }
        return stringBuffer.toString();
    }

}
