package com.iokays.designpatterns.command;

public class Stereo {

    private String name;

    public Stereo() {
        this.name = "";
    }

    public Stereo(String name) {
        this.name = name;
    }

    public void on() {
        System.out.println(this.name + " stereo: on");
    }

    public void off() {
        System.out.println(this.name + " stereo: off");
    }

    public void setCD() {
        System.out.println(this.name + " stereo: setCD");
    }

    public void setVolume(int volume) {
        System.out.println(this.name + " stereo: setVolume: " + volume);
    }

}
