package com.iokays.designpattern.abstractfactory.pizza;


/**
 * 纽约披萨店
 */
public class NYPizzaStore extends PizzaStore {

    @Override
    protected Pizza createPizza(String type) {
        Pizza pizza = null;
        PizzaIngredientFactory ingredientFactory = new NYPizzaIngredientFactory();

        if ("cheese".equals(type)) {
            pizza = new CheesePizza(ingredientFactory);
            pizza.setName("纽约风味芝士披萨");
        } else if ("clam".equals(type)) {
            pizza = new ClamPizza(ingredientFactory);
            pizza.setName("纽约风味蛤蜊披萨");
        }

        return pizza;
    }

}
