package com.iokays.designpattern.facade.hometheater;


/**
 * 灯光
 */
public class TheaterLights {

    /**
     * 描述
     */
    private String description;

    public TheaterLights() {
        this.description = "Theater Ceiling Lights";
    }

    /**
     * 打开灯光
     */
    public void dim(int i) {
        System.out.println("Theater Ceiling Lights dimming to 10%");
    }

    /**
     * 关闭灯光
     */
    public void on() {
        System.out.println("Theater Ceiling Lights on");
    }
}
