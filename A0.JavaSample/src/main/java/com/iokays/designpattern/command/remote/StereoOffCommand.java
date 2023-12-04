package com.iokays.designpattern.command.remote;


/**
 * 音响关闭命令
 */
public class StereoOffCommand implements Command {

    /**
     * 音响
     */
    private Stereo stereo;

    public StereoOffCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        stereo.off();
    }

    @Override
    public void undo() {
        stereo.on();
    }
}
