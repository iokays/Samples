package com.iokays.designpattern.observer.weather;


/**
 * 显示元素接口
 * DisplayElement接口只包含了一个方法， display()。 当布告板需要显示时， 调用此方法。
 */
public interface DisplayElement {

    /**
     * 显示
     */
    void display();

}
