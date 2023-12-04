package com.iokays.designpattern.decorator.beverage;


/**
 * 摩卡
 * 摩卡调料
 */
public class Mocha extends CondimentDecorator {

    Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
        description = "Mocha";
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", " + description;
    }

    @Override
    public double cost() {
        return .20D + beverage.cost();
    }
}
