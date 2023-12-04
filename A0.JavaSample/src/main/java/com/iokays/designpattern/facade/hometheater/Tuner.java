package com.iokays.designpattern.facade.hometheater;


/**
 * 调谐器
 */
public class Tuner {

    /**
     * 描述
     */
    private String description;

    public Tuner() {
        this.description = "Tuner";
    }

    /**
     * 打开调谐器
     */
    public void on() {
        System.out.println("Tuner on");
    }

    /**
     * 关闭调谐器
     */
    public void off() {
        System.out.println("Tuner off");
    }

    /**
     * 设置频率
     */
    public void setFrequency(double frequency) {
        System.out.println("Tuner setting frequency to " + frequency);
    }

}
