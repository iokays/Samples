package com.iokays.designpatterns.factorymethond;

import com.iokays.designpatterns.simplefactory.Pizza;

public class PizzaTestDrive {

    public static void main(String[] args) {
        PizzaStore nyStore = new NYPizzaStore();
        PizzaStore chicagoStore = new ChicagoPizzaStore();

        Pizza pizza = nyStore.orderPizza("cheese");
        System.out.printf("Ethan ordered a %s \n", pizza.getName());

        pizza = chicagoStore.orderPizza("cheese");
        System.out.printf("Joel ordered a %s \n", pizza.getName());
        
    }


}
