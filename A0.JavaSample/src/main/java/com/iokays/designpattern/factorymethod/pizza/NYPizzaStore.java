package com.iokays.designpattern.factorymethod.pizza;

import io.swagger.annotations.ApiModel;

@ApiModel("纽约披萨店")
public class NYPizzaStore extends PizzaStore {

    @Override
    protected Pizza createPizza(String type) {
        if ("cheese".equals(type)) {
            return new NYStyleCheesePizza();
        } else if ("clam".equals(type)) {
            return new NYStyleClamPizza();
        } else if ("veggie".equals(type)) {
            return new NYStyleVeggiePizza();
        } else {
            return null;
        }
    }

}
