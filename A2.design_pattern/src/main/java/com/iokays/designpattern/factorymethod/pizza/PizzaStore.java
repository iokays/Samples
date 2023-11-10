package com.iokays.designpattern.factorymethod.pizza;

import io.swagger.annotations.ApiModel;

@ApiModel("披萨店")
public abstract class PizzaStore {

    public Pizza orderPizza(String type) {
        Pizza pizza = createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }

    protected abstract Pizza createPizza(String type);

}
