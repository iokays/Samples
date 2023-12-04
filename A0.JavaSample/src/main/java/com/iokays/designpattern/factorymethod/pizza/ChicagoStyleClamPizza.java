package com.iokays.designpattern.factorymethod.pizza;


/**
 * 芝加哥风味蛤蜊披萨
 */
public class ChicagoStyleClamPizza extends Pizza {

    @Override
    void prepare() {
        System.out.println("ChicagoStyleClamPizza prepare");
    }

    @Override
    void bake() {
        System.out.println("ChicagoStyleClamPizza bake");
    }

    @Override
    void cut() {
        System.out.println("ChicagoStyleClamPizza cut");
    }

    @Override
    void box() {
        System.out.println("ChicagoStyleClamPizza box");
    }
}
