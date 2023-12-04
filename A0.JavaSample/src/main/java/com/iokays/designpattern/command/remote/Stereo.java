package com.iokays.designpattern.command.remote;


/**
 * 音响
 * 音响
 */
public class Stereo {

    private String location;

    public Stereo(String location) {
        this.location = location;
    }

    public void on() {
        System.out.println("Stereo is on");
    }

    public void off() {
        System.out.println("Stereo is off");
    }

    public void setCd() {
        System.out.println("Stereo is setCd");
    }

    public void setDvd() {
        System.out.println("Stereo is setDvd");
    }

    public void setRadio() {
        System.out.println("Stereo is setRadio");
    }

    public void setVolume(int volume) {
        System.out.println("Stereo volume is " + volume);
    }

}
