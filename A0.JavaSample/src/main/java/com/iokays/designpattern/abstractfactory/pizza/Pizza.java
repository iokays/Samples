package com.iokays.designpattern.abstractfactory.pizza;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

@ApiModel("披萨")
public abstract class Pizza {

    @ApiModelProperty("名称")
    String name;
    @ApiModelProperty("面团")
    Dough dough;
    @ApiModelProperty("酱料")
    Sauce sauce;
    @ApiModelProperty("蔬菜")
    Veggies veggies[];
    @ApiModelProperty("奶酪")
    Cheese cheese;
    @ApiModelProperty("腊肠")
    Pepperoni pepperoni;
    @ApiModelProperty("蛤蜊")
    Clams clam;

    @ApiOperation("准备")
    abstract void prepare();

    @ApiOperation("烘烤")
    void bake() {
        System.out.println("Bake for 25 minutes at 350");
    }

    @ApiOperation("切片")
    void cut() {
        System.out.println("Cutting the pizza into diagonal slices");
    }

    @ApiOperation("装盒")
    void box() {
        System.out.println("Place pizza in official PizzaStore box");
    }

    @ApiModelProperty("设置名称")
    void setName(String name) {
        this.name = name;
    }

    @ApiModelProperty("获取名称")
    String getName() {
        return name;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("---- ").append(name).append(" ----\n");
        if (dough != null) {
            result.append(dough);
            result.append("\n");
        }
        if (sauce != null) {
            result.append(sauce);
            result.append("\n");
        }
        if (cheese != null) {
            result.append(cheese);
            result.append("\n");
        }
        if (veggies != null) {
            for (Veggies veggie : veggies) {
                result.append(veggie);
                result.append("\n");
            }
        }
        if (clam != null) {
            result.append(clam);
            result.append("\n");
        }
        if (pepperoni != null) {
            result.append(pepperoni);
            result.append("\n");
        }
        return result.toString();
    }



}
