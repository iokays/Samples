package com.iokays.designpatterns.factorymethond;

import com.iokays.designpatterns.simplefactory.Pizza;

public abstract class PizzaStore {

     public Pizza orderPizza(String type) {

          Pizza pizza = createPizza(type);

          pizza.prepare();
          pizza.bake();
          pizza.cut();
          pizza.box();

          return pizza;
     }

     abstract Pizza createPizza(String type);

}
