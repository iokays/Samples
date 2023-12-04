package com.iokays.designpattern.iterator.menu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

import java.util.Iterator;

@ApiModel(value = "餐厅菜单")
public class DinerMenu {

    @ApiModelProperty(value = "菜单项最大数量")
    private static final int MAX_ITEMS = 6;

    @ApiModelProperty(value = "菜单项数量")
    private int numberOfItems = 0;

    @ApiModelProperty(value = "菜单项")
    private MenuItem[] menuItems;

    public DinerMenu() {
        menuItems = new MenuItem[MAX_ITEMS];

        addItem("Vegetarian BLT", "(Fakin') Bacon with lettuce & tomato on whole wheat", true, 2.99);
        addItem("BLT", "Bacon with lettuce & tomato on whole wheat", false, 2.99);
        addItem("Soup of the day", "Soup of the day, with a side of potato salad", false, 3.29);
        addItem("Hotdog", "A hot dog, with saurkraut, relish, onions, topped with cheese", false, 3.05);
    }

    @ApiOperation(value = "添加菜单项", notes = "添加菜单项")
    public void addItem(String name, String description, boolean vegetarian, double price) {
        MenuItem menuItem = new MenuItem(name, description, vegetarian, price);

        if (numberOfItems >= MAX_ITEMS) {
            System.err.println("Sorry, menu is full! Can't add item to menu");
        } else {
            menuItems[numberOfItems] = menuItem;
            numberOfItems++;
        }
    }

    @ApiOperation(value = "获取菜单项", notes = "获取菜单项")
    public MenuItem[] getMenuItems() {
        return menuItems;
    }

    @ApiOperation(value = "创建迭代器", notes = "创建迭代器")
    public Iterator createIterator() {
        return new DinerMenuIterator(menuItems);
    }

    // other menu methods here

}