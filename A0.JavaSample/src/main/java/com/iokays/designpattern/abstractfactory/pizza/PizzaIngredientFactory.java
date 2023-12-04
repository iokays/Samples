package com.iokays.designpattern.abstractfactory.pizza;


/**
 * 披萨原料工厂
 */
public interface PizzaIngredientFactory {

    /**
     * 创建面团
     */
    Dough createDough();

    /**
     * 创建酱料
     */
    Sauce createSauce();

    /**
     * 创建奶酪
     */
    Cheese createCheese();

    /**
     * 创建蔬菜
     */
    Veggies[] createVeggies();

    /**
     * 创建腊肠
     */
    Pepperoni createPepperoni();

    /**
     * 创建蛤蜊
     */
    Clams createClam();

}
