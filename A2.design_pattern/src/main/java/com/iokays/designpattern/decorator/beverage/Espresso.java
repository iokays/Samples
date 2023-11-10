package com.iokays.designpattern.decorator.beverage;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "浓缩咖啡")
public class Espresso extends Beverage {

    public Espresso() {
        description = "Espresso";
    }

    @Override
    public double cost() {
        return 1.99D;
    }
}
