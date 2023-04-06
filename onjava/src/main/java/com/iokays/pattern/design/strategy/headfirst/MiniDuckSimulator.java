package com.iokays.pattern.design.strategy.headfirst;

public class MiniDuckSimulator {

    public static void main(String[] args) {
        Duck mallard = new MallardDuck();
        mallard.performQuack();
        mallard.performFly();

        System.out.println("---");

        Duck model = new RedHeadDuck();
        model.performFly();
        //改变飞行行为
        model.setFlyBehavior(new FlyNoWay());
        model.performFly();
    }
}