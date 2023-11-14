package com.iokays.designpattern.abstractfactory.pizza;

import io.swagger.annotations.ApiOperation;

@ApiOperation("披萨原料工厂")
public interface PizzaIngredientFactory {

    @ApiOperation("创建面团")
    Dough createDough();
    @ApiOperation("创建酱料")
    Sauce createSauce();
    @ApiOperation("创建奶酪")
    Cheese createCheese();
    @ApiOperation("创建蔬菜")
    Veggies[] createVeggies();
    @ApiOperation("创建腊肠")
    Pepperoni createPepperoni();
    @ApiOperation("创建蛤蜊")
    Clams createClam();

}
