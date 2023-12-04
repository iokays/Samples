package com.iokays.designpattern.facade.hometheater;


/**
 * DVD播放器
 */
public class DvdPlayer {

    /**
     * 描述
     */
    private String description;

    public DvdPlayer() {
        this.description = "DVD Player";
    }

    /**
     * 打开DVD播放器
     */
    public void on() {
        System.out.println("DvdPlayer on");
    }

    /**
     * 关闭DVD播放器
     */
    public void off() {
        System.out.println("DvdPlayer off");
    }

    /**
     * 播放DVD
     */
    public void eject() {
        System.out.println("DvdPlayer eject");
    }

    /**
     * 停止播放DVD
     */
    public void play(String movie) {
        System.out.println("DvdPlayer playing " + movie);
    }

    /**
     * 设置环绕声
     */
    public void stop() {
        System.out.println("DvdPlayer stop");
    }

}
