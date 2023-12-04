package com.iokays.designpattern.command.remote;


/**
 * 车库门
 */
public class GarageDoor {

    /**
     * 位置
     */
    private String location;

    public GarageDoor(String location) {
        this.location = location;
    }

    /**
     * 上升", notes = "上升
     */
    public void up() {
        System.out.println("GarageDoor is up");
    }

    /**
     * 下降", notes = "下降
     */
    public void down() {
        System.out.println("GarageDoor is down");
    }

    /**
     * 停止", notes = "停止
     */
    public void stop() {
        System.out.println("GarageDoor is stop");
    }

    /**
     * 打开灯", notes = "打开
     */
    public void lightOn() {
        System.out.println("GarageDoor light is on");
    }

    /**
     * 关闭灯", notes = "关闭
     */
    public void lightOff() {
        System.out.println("GarageDoor light is off");
    }

}
