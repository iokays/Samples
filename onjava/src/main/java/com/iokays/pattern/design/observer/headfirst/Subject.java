package com.iokays.pattern.design.observer.headfirst;


/**
 *  这是一个主题接口
 *  对象使用此接口注册为观察者， 或者把自己从观察者中删除
 */
public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObserves();
}
