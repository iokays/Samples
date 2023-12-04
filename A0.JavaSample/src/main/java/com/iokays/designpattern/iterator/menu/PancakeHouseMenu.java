package com.iokays.designpattern.iterator.menu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

import java.util.Iterator;

@ApiModel(value = "煎饼屋菜单")
public class PancakeHouseMenu {

    @ApiModelProperty(value = "菜单项")
    MenuItem[] menuItems;

    @ApiModelProperty(value = "菜单项数量")
    static final int MAX_ITEMS = 6;

    @ApiModelProperty(value = "菜单项位置")
    int numberOfItems = 0;

    public PancakeHouseMenu() {
        menuItems = new MenuItem[MAX_ITEMS];

        addItem("K&B's Pancake Breakfast",
                "Pancakes with scrambled eggs, and toast",
                true,
                2.99);
        addItem("Regular Pancake Breakfast",
                "Pancakes with fried eggs, sausage",
                false,
                2.99);
        addItem("Blueberry Pancakes",
                "Pancakes made with fresh blueberries",
                true,
                3.49);
        addItem("Waffles",
                "Waffles, with your choice of blueberries or strawberries",
                true,
                3.59);
    }

    @ApiOperation(value = "添加菜单项", notes = "添加菜单项")
    public void addItem(String name, String description, boolean vegetarian, double price) {
        MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
        if (numberOfItems >= MAX_ITEMS) {
            System.err.println("Sorry, menu is full!  Can't add item to menu");
        }
        else {
            menuItems[numberOfItems] = menuItem;
            numberOfItems = numberOfItems + 1;
        }
    }

    @ApiOperation(value = "获取菜单项", notes = "获取菜单项")
    public MenuItem[] getMenuItems() {
        return menuItems;
    }

    @ApiOperation(value = "创建迭代器", notes = "创建迭代器")
    public Iterator createIterator() {
        return new PancakeHouseMenuIterator(menuItems);
    }

    // other menu methods here
}
