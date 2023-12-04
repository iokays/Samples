package com.iokays.designpattern.command.remote;


/**
 * 车库门下降命令
 */
public class GarageDoorDownCommand implements Command {

    /**
     * 车库门
     */
    private GarageDoor garageDoor;

    public GarageDoorDownCommand(GarageDoor garageDoor) {
        this.garageDoor = garageDoor;
    }

    @Override
    public void execute() {
        garageDoor.down();
    }

    @Override
    public void undo() {
        garageDoor.up();
    }

}
