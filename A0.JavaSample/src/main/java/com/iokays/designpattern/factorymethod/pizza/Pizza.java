package com.iokays.designpattern.factorymethod.pizza;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;

@ApiModel("披萨")
public abstract class Pizza {

    @ApiOperation("准备")
    abstract void prepare();

    @ApiOperation("烘焙")
    abstract void bake();

    @ApiOperation("切割")
    abstract void cut();

    @ApiOperation("装盒")
    abstract void box();

}
