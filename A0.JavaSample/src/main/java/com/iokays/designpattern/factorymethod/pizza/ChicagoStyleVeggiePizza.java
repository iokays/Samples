package com.iokays.designpattern.factorymethod.pizza;


/**
 * 芝加哥风味素食披萨
 */
public class ChicagoStyleVeggiePizza extends Pizza {

    @Override
    void prepare() {
        System.out.println("ChicagoStyleVeggiePizza prepare");
    }

    @Override
    void bake() {
        System.out.println("ChicagoStyleVeggiePizza bake");
    }

    @Override
    void cut() {
        System.out.println("ChicagoStyleVeggiePizza cut");
    }

    @Override
    void box() {
        System.out.println("ChicagoStyleVeggiePizza box");
    }

}
