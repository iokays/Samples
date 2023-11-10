package com.iokays.designpattern.observer.weather;

import io.swagger.annotations.*;

@ApiModel(value = "观察者接口", description = "所有的气象站观察者都必须实现此接口， 这样， 主题在需要通知观察者时， 有了一个共同的接口。")
public interface Observer {

    @ApiOperation(value = "更新")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "temperature", value = "温度", dataType = "float"),
            @ApiImplicitParam(name = "humidity", value = "湿度", dataType = "float"),
            @ApiImplicitParam(name = "pressure", value = "气压", dataType = "float")
    })
    void update(float temperature, float humidity, float pressure);

}
