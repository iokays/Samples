package com.iokays.designpatterns.factorymethond;

import com.iokays.designpatterns.simplefactory.*;

public class NYPizzaStore extends PizzaStore {
    @Override
    Pizza createPizza(String type) {
        Pizza pizza = null;
        if (type.equals("cheese")) {
            return new NYStyleCheesePizza();
        } else if (type.equals("pepperoni")) {
            return new NYStylePepperoniPizza();
        } else if (type.equals("clam")) {
            return new NYStyleClamPizza();
        } else if (type.equals("veggie")) {
            return new NYStyleVeggiePizza();
        }

        return pizza;
    }
}
