package com.iokays.designpatterns.decorator;

public class DarkRoast extends Beverage {

    public DarkRoast() {
        description = "DarkRoast";
    }

    @Override
    public double cost() {
        return 9.99;
    }
}
