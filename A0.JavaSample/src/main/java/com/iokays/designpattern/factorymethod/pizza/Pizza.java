package com.iokays.designpattern.factorymethod.pizza;


/**
 * 披萨
 */
public abstract class Pizza {

    /**
     * 准备
     */
    abstract void prepare();

    /**
     * 烘焙
     */
    abstract void bake();

    /**
     * 切割
     */
    abstract void cut();

    /**
     * 装盒
     */
    abstract void box();

}
