package com.iokays.designpattern.decorator.beverage;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "调料")
public abstract class CondimentDecorator extends Beverage {

    abstract String getDescription();

}
