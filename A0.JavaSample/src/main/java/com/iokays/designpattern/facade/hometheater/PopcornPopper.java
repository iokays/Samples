package com.iokays.designpattern.facade.hometheater;


/**
 * 爆米花机
 */
public class PopcornPopper {

    /**
     * 描述
     */
    private String description;

    public PopcornPopper() {
        this.description = "Popcorn Popper";
    }

    /**
     * 打开爆米花机
     */
    public void on() {
        System.out.println("PopcornPopper on");
    }

    /**
     * 关闭爆米花机
     */
    public void off() {
        System.out.println("PopcornPopper off");
    }

    /**
     * 开始爆米花
     */
    public void pop() {
        System.out.println("PopcornPopper popping popcorn!");
    }
}
