package com.iokays.designpattern.decorator.beverage;


/**
 * 混合咖啡
 */
public class HouseBlend extends Beverage {

    public HouseBlend() {
        description = "House Blend Coffee";
    }

    @Override
    public double cost() {
        return .89D;
    }

}
