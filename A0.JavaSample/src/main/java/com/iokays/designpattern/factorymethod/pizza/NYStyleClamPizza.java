package com.iokays.designpattern.factorymethod.pizza;


/**
 * 纽约风味蛤蜊披萨
 */
public class NYStyleClamPizza extends Pizza {

    @Override
    void prepare() {
        System.out.println("NYStyleClamPizza prepare");
    }

    @Override
    void bake() {
        System.out.println("NYStyleClamPizza bake");
    }

    @Override
    void cut() {
        System.out.println("NYStyleClamPizza cut");
    }

    @Override
    void box() {
        System.out.println("NYStyleClamPizza box");
    }

}
