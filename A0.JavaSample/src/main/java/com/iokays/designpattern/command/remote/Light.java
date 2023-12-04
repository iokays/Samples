package com.iokays.designpattern.command.remote;


import java.io.Serializable;

/**
 * 电灯
 * 电灯
 */
public class Light implements Serializable {

    private String location;

    public Light() {
        this("Light");
    }

    public Light(String location) {
        this.location = location;
    }

    /**
     * 打开电灯", notes = "打开电灯
     */
    public void on() {
        System.out.println("Light is on");
    }

    /**
     * 关闭电灯", notes = "关闭电灯
     */
    public void off() {
        System.out.println("Light is off");
    }
}
