package com.iokays.designpattern.decorator.beverage;


/**
 * 饮料
 */
public abstract class Beverage {

    /**
     * 饮料描述
     */
    String description = "Unknown Beverage";

    /**
     * 获取饮料描述
     *
     * @return
     */
    String getDescription() {
        return description;
    }

    /**
     * 计算饮料价格
     *
     * @return
     */
    abstract double cost();

}
