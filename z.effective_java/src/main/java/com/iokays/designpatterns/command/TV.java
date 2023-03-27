package com.iokays.designpatterns.command;

public class TV {

    private String location;

    public TV(String location) {
        this.location = location;
    }

    public void on() {
        System.out.printf("%s's tv is on", location);
    }

    public void off() {
        System.out.printf("%s's tv is off", location);
    }


}
