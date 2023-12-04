package com.iokays.designpattern.observer.weather;


/**
 * 观察者接口
 * 所有的气象站观察者都必须实现此接口， 这样， 主题在需要通知观察者时， 有了一个共同的接口。
 */
public interface Observer {

    /**
     * 更新
     *
     * @param temperature 温度
     * @param humidity    湿度
     * @param pressure    气压
     */
    void update(float temperature, float humidity, float pressure);

}
