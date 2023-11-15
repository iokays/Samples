package com.iokays.designpattern.adapter.duck;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "绿头鸭")
public class MallardDuck implements Duck {

    @Override
    public void quack() {
        System.out.println("Quack");
    }

    @Override
    public void fly() {
        System.out.println("I'm flying");
    }

}
