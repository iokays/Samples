package com.iokays.designpatterns.abstractfactory;

import com.iokays.designpatterns.abstractfactory.Pizza;

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
