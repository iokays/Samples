package com.iokays.designpattern.command.remote;


/**
 * 打开吊扇命令
 */
public class CeilingFanOnCommand implements Command {

    /**
     * 吊扇
     */
    private CeilingFan ceilingFan;

    public CeilingFanOnCommand(CeilingFan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }

    @Override
    public void execute() {
        ceilingFan.high();
    }

    @Override
    public void undo() {
        ceilingFan.off();
    }
}
