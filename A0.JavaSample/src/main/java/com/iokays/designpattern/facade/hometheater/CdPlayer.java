package com.iokays.designpattern.facade.hometheater;


/**
 * CD播放器
 */
public class CdPlayer {


    /**
     * 描述
     */
    private String description;

    public CdPlayer() {
        this.description = "CD Player";
    }

    /**
     * 打开CD播放器
     */
    public void on() {
        System.out.println("CdPlayer on");
    }

    /**
     * 关闭CD播放器
     */
    public void off() {
        System.out.println("CdPlayer off");
    }

    /**
     * 播放CD
     */
    public void eject() {
        System.out.println("CdPlayer eject");
    }

    /**
     * 停止播放CD
     */
    public void play(String cdTitle) {
        System.out.println("CdPlayer playing " + cdTitle);
    }

}
