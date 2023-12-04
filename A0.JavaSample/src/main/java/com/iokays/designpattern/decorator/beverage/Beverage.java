package com.iokays.designpattern.decorator.beverage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

@ApiModel(value = "饮料")
public abstract class Beverage {

    @ApiModelProperty(value = "饮料描述")
    String description = "Unknown Beverage";

    @ApiOperation(value = "饮料描述")
    String getDescription() {
        return description;
    }

    @ApiOperation(value = "饮料价格")
    abstract double cost();

}
