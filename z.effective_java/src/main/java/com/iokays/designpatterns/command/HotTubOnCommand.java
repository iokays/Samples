package com.iokays.designpatterns.command;

public class HotTubOnCommand implements Command {

    private HotTub hotTub;

    public HotTubOnCommand(HotTub hotTub) {
        this.hotTub = hotTub;
    }

    @Override
    public void execute() {
        hotTub.on();
    }

    @Override
    public void undo() {
        hotTub.off();
    }
}
