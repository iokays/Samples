package com.iokays.designpatterns.command;

public class RemoteControlWithUndo {

    private static final int size = 7;

    Command[] onCommands;
    Command[] offCommands;
    Command undoCommand;

    public RemoteControlWithUndo() {

        this.onCommands = new Command[size];
        this.offCommands = new Command[size];

        Command noCommand = new NoCommand();

        for (int i = 0; i < size; i++) {
            this.onCommands[i] = noCommand;
            this.offCommands[i] = noCommand;
        }

        undoCommand = noCommand;

    }

    public void setCommand(int slot, Command onCommand, Command offCommand) {
        this.onCommands[slot] = onCommand;
        this.offCommands[slot] = offCommand;
    }

    public void onButtonWasPushed(int slot) {
        this.onCommands[slot].execute();
        this.undoCommand = this.onCommands[slot];
    }

    public void offButtonWasPushed(int slot) {
        this.offCommands[slot].execute();
        this.undoCommand = this.offCommands[slot];
    }

    public void undoButtonWasPushed() {
        this.undoCommand.undo();
    }

    @Override
    public String toString() {
        return new org.apache.commons.lang3.builder.ToStringBuilder(this)
                .append("onCommands", onCommands)
                .append("offCommands", offCommands)
                .toString();
    }
}
