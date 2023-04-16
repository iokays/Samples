package com.iokays.pattern.design.decorator.headfirst;

public abstract class CondimentDecorator extends Beverage {
    Beverage beverage;

    public abstract String getDescription();
}
