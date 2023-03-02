package com.iokays.designpatterns.factorymethond;

import com.iokays.designpatterns.simplefactory.Pizza;

public class ChicagoPizzaStore extends PizzaStore {
    @Override
    Pizza createPizza(String type) {
        Pizza pizza = null;
        if (type.equals("cheese")) {
            return new ChicagoCheesePizza();
        } else if (type.equals("pepperoni")) {
            return new ChicagoPepperoniPizza();
        } else if (type.equals("clam")) {
            return new ChicagoClamPizza();
        } else if (type.equals("veggie")) {
            return new ChicagoVeggiePizza();
        }

        return pizza;
    }
}
