package com.iokays.designpatterns.decorator;

public class Whip extends CondimentDecorator {

    Beverage beverage;

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double cost() {
        return 0.30 + beverage.cost();
    }

    @Override
    public String getDescription() {
        return this.beverage.getDescription() + ", Ship";
    }
}
