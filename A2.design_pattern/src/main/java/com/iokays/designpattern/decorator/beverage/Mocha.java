package com.iokays.designpattern.decorator.beverage;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "摩卡", description = "摩卡调料")
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
