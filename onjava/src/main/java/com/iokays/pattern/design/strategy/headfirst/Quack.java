package com.iokays.pattern.design.strategy.headfirst;

public class Quack implements QuackBehavior {
    public void quack() {
        System.out.println("Quack");
    }
}