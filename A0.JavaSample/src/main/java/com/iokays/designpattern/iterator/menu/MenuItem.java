package com.iokays.designpattern.iterator.menu;


import java.io.Serializable;

/**
 * 菜单项
 */
public class MenuItem implements Serializable {

    /**
     * 菜单项名称
     */
    private String name;

    /**
     * 菜单项描述
     */
    private String description;

    /**
     * 是否为素食
     */
    private boolean vegetarian;

    /**
     * 菜单项价格
     */
    private double price;

    public MenuItem(String name, String description, boolean vegetarian, double price) {
        this.name = name;
        this.description = description;
        this.vegetarian = vegetarian;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public double getPrice() {
        return price;
    }
}
