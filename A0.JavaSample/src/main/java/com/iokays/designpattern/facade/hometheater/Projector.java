package com.iokays.designpattern.facade.hometheater;


/**
 * 投影仪
 */
public class Projector {

    /**
     * 描述
     */
    private String description;

    public Projector() {
        this.description = "Projector";
    }

    /**
     * 打开投影仪
     */
    public void on() {
        System.out.println("Projector on");
    }

    /**
     * 设置投影仪宽屏模式
     */
    public void wideScreenMode() {
        System.out.println("Projector in widescreen mode (16x9 aspect ratio)");
    }

    /**
     * 关闭投影仪
     */
    public void off() {
        System.out.println("Projector off");
    }
}
