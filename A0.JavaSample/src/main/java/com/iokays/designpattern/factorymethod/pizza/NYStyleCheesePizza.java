package com.iokays.designpattern.factorymethod.pizza;


/**
 * 纽约风味芝士披萨
 */
public class NYStyleCheesePizza extends Pizza {

    @Override
    void prepare() {
        System.out.println("NYStyleCheesePizza prepare");
    }

    @Override
    void bake() {
        System.out.println("NYStyleCheesePizza bake");
    }

    @Override
    void cut() {
        System.out.println("NYStyleCheesePizza cut");
    }

    @Override
    void box() {
        System.out.println("NYStyleCheesePizza box");
    }

}
