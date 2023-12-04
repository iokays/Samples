package com.iokays.designpattern.singleton;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

@ApiModel(value = "单例模式", description = "单例模式违反了一个类一个职责的原则,(单例类不只是负责管理自己的实例,[并提供了全局访问]).")
public class Singleton {

    @ApiModelProperty("单例实例")
    private static Singleton uniqueInstance;

    private Singleton() {
    }

    @ApiOperation(value = "获取单例实例", notes = "访问点, 懒加载, 但是非线程安全")
    public static Singleton getInstance() {
        if (null == uniqueInstance) {
            uniqueInstance = new Singleton();
        }
        return uniqueInstance;
    }

}
