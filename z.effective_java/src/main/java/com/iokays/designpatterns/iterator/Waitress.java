package com.iokays.designpatterns.iterator;

import com.google.common.collect.Lists;

import java.util.ArrayList;

public class Waitress {

    ArrayList menus;

    public Waitress(ArrayList menus) {
        this.menus = menus;
    }

    public Waitress(Menu pancakeHouseMenu, Menu dinerMenu, Menu cafeMenu) {
        this(Lists.newArrayListWithCapacity(3));
        menus.add(pancakeHouseMenu);
        menus.add(dinerMenu);
        menus.add(cafeMenu);
    }

    public void printMenu() {
        for (Object menu : menus) {
            printMenu(((Menu)menu).createIterator());
        }
    }

    private void printMenu(Iterator iterator) {
        System.out.printf("\n");
        while (iterator.hasNext()) {
            MenuItem menuItem = (MenuItem) iterator.next();
            System.out.printf("%s, %s -- %s\n", menuItem.getName(), menuItem.getPrice(), menuItem.getDescription());
        }
    }

}
