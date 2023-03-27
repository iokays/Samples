package com.iokays.designpatterns.strategy;

public abstract class Duck {

    protected FlyBehavior flyBehavior;
    protected QuackBehavior quackBehavior;

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }

    public Duck() {
    }

    void performQuack() {
        quackBehavior.quack();
    }

    void performFly() {
        flyBehavior.fly();
    }

    public abstract void display();

    public void swim() {
        System.out.println("All ducks float, even decoys!");
    }

}
