package com.iokays.designpattern.adapter.duck;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;

@ApiModel(value = "火鸡")
public interface Turkey {

    @ApiOperation(value = "咯咯叫")
    void gobble();

    @ApiOperation(value = "飞")
    void fly();
}
