package com.iokays.designpattern.singleton;


/**
 * 单例模式
 * 线程安全的单例模式(急迫)
 */
public class Singleton2 {

    private static Singleton2 uniqueInstance = new Singleton2();

    private Singleton2() {
    }

    /**
     * 获取单例实例", notes = "访问点, 急迫的创建实例, 但是可能会造成资源浪费
     */
    public static Singleton2 getInstance() {
        return uniqueInstance;
    }
}
