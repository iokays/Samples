package com.iokays.designpattern.adapter.duck;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;

@ApiModel(value = "鸭子")
public interface Duck {

    @ApiOperation(value = "呱呱叫")
    void quack();

    @ApiOperation(value = "飞")
    void fly();

}
