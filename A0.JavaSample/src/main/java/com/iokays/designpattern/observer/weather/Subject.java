package com.iokays.designpattern.observer.weather;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;

@ApiModel(value = "主题接口", description = "主题接口")
public interface Subject {

    @ApiOperation(value = "注册观察者")
    void registerObserver(Observer observer);

    @ApiOperation(value = "移除观察者")
    void removeObserver(Observer observer);

    @ApiOperation(value = "通知观察者")
    void notifyObservers();

}
