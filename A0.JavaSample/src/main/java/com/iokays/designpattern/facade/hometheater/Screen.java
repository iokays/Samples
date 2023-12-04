package com.iokays.designpattern.facade.hometheater;


/**
 * 屏幕
 */
public class Screen {


    /**
     * 描述
     */
    private String description;

    public Screen() {
        this.description = "Screen";
    }

    /**
     * 打开屏幕
     */
    public void up() {
        System.out.println("Screen going up");
    }

    /**
     * 关闭屏幕
     */
    public void down() {
        System.out.println("Screen going down");
    }

}
