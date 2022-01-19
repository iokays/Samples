package com.iokays.designpatterns.simplefactory;

public abstract class Pizza {

    void prepare() {
        System.out.println("prepare");
    }

    void bake() {
        System.out.println("bake");
    }

    void cut() {
        System.out.println("cut");
    }

    void box() {
        System.out.println("box");
    }
}
