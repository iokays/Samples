package com.iokays.designpattern.factorymethod.pizza;


/**
 * 芝加哥风味芝士披萨
 */
public class ChicagoStyleCheesePizza extends Pizza {

    @Override
    void prepare() {
        System.out.println("ChicagoStyleCheesePizza prepare");
    }

    @Override
    void bake() {
        System.out.println("ChicagoStyleCheesePizza bake");
    }

    @Override
    void cut() {
        System.out.println("ChicagoStyleCheesePizza cut");
    }

    @Override
    void box() {
        System.out.println("ChicagoStyleCheesePizza box");
    }

}
