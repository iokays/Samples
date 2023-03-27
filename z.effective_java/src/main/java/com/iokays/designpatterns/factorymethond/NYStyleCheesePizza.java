package com.iokays.designpatterns.factorymethond;

import com.iokays.designpatterns.simplefactory.Pizza;

public class NYStyleCheesePizza extends Pizza {

    public NYStyleCheesePizza() {
        name = "NY style Sauce and Cheese Pizza";
        dough = "Thin Crust Dough";
        sauce = "Marinara Sauce";

        toppings.add("Grated Reggiano Cheese");
    }
}
