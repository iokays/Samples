package com.iokays.designpattern.observer.weather;


/**
 * 主题接口
 * 主题接口
 */
public interface Subject {

    /**
     * 注册观察者
     */
    void registerObserver(Observer observer);

    /**
     * 移除观察者
     */
    void removeObserver(Observer observer);

    /**
     * 通知观察者
     */
    void notifyObservers();

}
