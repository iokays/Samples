package com.iokays.designpattern.adapter.duck;


import java.util.stream.IntStream;

/**
 * 火鸡适配器
 */
public class TurkeyAdapter implements Duck {

    /**
     * 火鸡
     */
    Turkey turkey;

    public TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }

    @Override
    public void quack() {
        turkey.gobble();
    }

    /**
     * 飞", notes = "火鸡飞行距离短，所以要飞5次
     */
    @Override
    public void fly() {
        IntStream.range(0, 5).forEach(i -> turkey.fly());
    }
}
