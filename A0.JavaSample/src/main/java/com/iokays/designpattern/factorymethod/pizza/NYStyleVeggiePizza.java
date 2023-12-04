package com.iokays.designpattern.factorymethod.pizza;


/**
 * 纽约风味素食披萨
 */
public class NYStyleVeggiePizza extends Pizza {
    @Override
    void prepare() {
        System.out.println("NYStyleVeggiePizza prepare");
    }

    @Override
    void bake() {
        System.out.println("NYStyleVeggiePizza bake");
    }

    @Override
    void cut() {
        System.out.println("NYStyleVeggiePizza cut");
    }

    @Override
    void box() {
        System.out.println("NYStyleVeggiePizza box");
    }
}
