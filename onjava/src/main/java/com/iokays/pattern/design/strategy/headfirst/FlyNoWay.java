package com.iokays.pattern.design.strategy.headfirst;

public class FlyNoWay implements FlyBehavior {
    public void fly() {
        System.out.println("I can't fly");
    }
}