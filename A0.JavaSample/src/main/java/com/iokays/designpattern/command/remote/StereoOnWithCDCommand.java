package com.iokays.designpattern.command.remote;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "打开音响命令", description = "打开音响命令")
public class StereoOnWithCDCommand implements Command {

    private Stereo stereo;

    public StereoOnWithCDCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        stereo.on();
        stereo.setCd();
        stereo.setVolume(11);
    }

    @Override
    public void undo() {
        stereo.off();
    }

}
