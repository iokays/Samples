package com.iokays.designpattern.facade.hometheater;


/**
 * 放大器
 */
public class Amplifier {

    /**
     * 描述
     */
    private String description;

    public Amplifier() {
        this.description = "Top-O-Line Amplifier";
    }

    /**
     * 打开放大器
     */
    public void on() {
        System.out.println("Amplifier on");
    }

    /**
     * 设置放大器的DVD播放器
     */
    public void setDvd(DvdPlayer dvd) {
        System.out.println("Amplifier set dvd");
    }

    /**
     * 设置放大器的调谐器
     */
    public void setCd(CdPlayer cd) {
        System.out.println("Amplifier set cd");
    }

    /**
     * 设置环绕立体声
     */
    public void setSurroundSound() {
        System.out.println("Amplifier set surround sound");
    }

    /**
     * 设置音量
     */
    public void setVolume(int i) {
        System.out.println("Amplifier set volume");
    }

    /**
     * 关闭放大器
     */
    public void off() {
        System.out.println("Amplifier off");
    }

    /**
     * 设置立体声
     */
    public void setStereoSound() {
        System.out.println("Amplifier set stereo sound");
    }

    /**
     * 设置调谐器
     */
    public void setTuner(Tuner tuner) {
        System.out.println("Amplifier set tuner");
    }

}
