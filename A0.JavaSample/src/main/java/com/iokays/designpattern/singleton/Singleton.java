package com.iokays.designpattern.singleton;


/**
 * 单例模式
 * <p>
 * 单例模式违反了一个类一个职责的原则,(单例类不只是负责管理自己的实例,[并提供了全局访问]).
 */
public class Singleton {

    /**
     * 单例实例
     */
    private static Singleton uniqueInstance;

    private Singleton() {
    }

    /**
     * 获取单例实例 (懒加载, 但是非线程安全)
     *
     * @return
     */
    public static Singleton getInstance() {
        if (null == uniqueInstance) {
            uniqueInstance = new Singleton();
        }
        return uniqueInstance;
    }

}
