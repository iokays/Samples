package com.iokays.designpattern.templatemethod.coffee;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;

@ApiModel("带钩子的咖啡因饮料")
public class CaffeineBeverageWithHook {

    public void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        if (customerWantsCondiments()) {
            addCondiments();
        }
    }

    @ApiOperation("烧水")
    public void boilWater() {
        System.out.println("Boiling water");
    }

    @ApiOperation("冲泡")
    public void brew() {
        System.out.println("Dripping Coffee through filter");
    }

    @ApiOperation("倒入杯中")
    public void pourInCup() {
        System.out.println("Pouring into cup");
    }

    @ApiOperation("加入调料")
    public void addCondiments() {
        System.out.println("Adding Sugar and Milk");
    }

    @ApiOperation("是否加入调料")
    public boolean customerWantsCondiments() {
        return true;
    }

}
