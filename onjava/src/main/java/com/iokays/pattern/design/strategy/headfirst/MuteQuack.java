package com.iokays.pattern.design.strategy.headfirst;

public class MuteQuack implements QuackBehavior {
    public void quack() {
        System.out.println("<< Silence >>");
    }
}