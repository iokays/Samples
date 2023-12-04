package com.iokays.designpattern.command.remote;


/**
 * 空命令
 * 空命令
 */
public class NoCommand implements Command {

    @Override
    public void execute() {
    }

    @Override
    public void undo() {
    }

}
