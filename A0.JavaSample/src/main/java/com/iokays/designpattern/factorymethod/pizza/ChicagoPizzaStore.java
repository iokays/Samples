package com.iokays.designpattern.factorymethod.pizza;

import io.swagger.annotations.ApiModel;

@ApiModel("芝加哥风味披萨店")
public class ChicagoPizzaStore extends PizzaStore {

    @Override
    protected Pizza createPizza(String type) {
        if ("cheese".equals(type)) {
            return new ChicagoStyleCheesePizza();
        } else if ("clam".equals(type)) {
            return new ChicagoStyleClamPizza();
        } else if ("veggie".equals(type)) {
            return new ChicagoStyleVeggiePizza();
        } else {
            return null;
        }
    }

}
