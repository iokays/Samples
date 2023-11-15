package com.iokays.designpattern.adapter.duck;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

import java.util.stream.IntStream;

@ApiModel(value = "火鸡适配器")
public class TurkeyAdapter implements Duck {

    @ApiModelProperty(value = "火鸡")
    Turkey turkey;

    public TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }

    @Override
    public void quack() {
        turkey.gobble();
    }

    @ApiOperation(value = "飞", notes = "火鸡飞行距离短，所以要飞5次")
    @Override
    public void fly() {
        IntStream.range(0, 5).forEach(i -> turkey.fly());
    }
}
