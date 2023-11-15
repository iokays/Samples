package com.iokays.designpattern.adapter.duck;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "火鸡")
public class WildTurkey implements Turkey {

    @Override
    public void gobble() {
        System.out.println("Gobble gobble");
    }

    @Override
    public void fly() {
        System.out.println("I'm flying a short distance");
    }

}
