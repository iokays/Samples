package com.iokays.designpattern.observer.weather;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;

@ApiModel(value = "显示元素接口", description = "DisplayElement接口只包含了一个方法， display()。 当布告板需要显示时， 调用此方法。")
public interface DisplayElement {

    @ApiOperation(value = "显示")
    void display();

}
