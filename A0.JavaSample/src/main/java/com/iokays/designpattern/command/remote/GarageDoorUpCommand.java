package com.iokays.designpattern.command.remote;


/**
 * 车库门上升命令
 */
public class GarageDoorUpCommand implements Command {

    /**
     * 车库门
     */
    private GarageDoor garageDoor;

    public GarageDoorUpCommand(GarageDoor garageDoor) {
        this.garageDoor = garageDoor;
    }

    @Override
    public void execute() {
        garageDoor.up();
    }

    @Override
    public void undo() {
        garageDoor.down();
    }

}
