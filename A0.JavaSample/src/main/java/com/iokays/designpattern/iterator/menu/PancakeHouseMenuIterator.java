package com.iokays.designpattern.iterator.menu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

import java.util.Iterator;

@ApiModel(value = "煎饼屋菜单迭代器")
public class PancakeHouseMenuIterator implements Iterator<MenuItem> {

    @ApiModelProperty(value = "菜单项")
    MenuItem[] menuItems;

    @ApiModelProperty(value = "菜单项位置")
    int position = 0;

    public PancakeHouseMenuIterator(MenuItem[] menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    @ApiOperation(value = "获取下一个菜单项", notes = "获取下一个菜单项")
    public MenuItem next() {
        MenuItem menuItem = menuItems[position];
        position = position + 1;
        return menuItem;
    }

    @ApiOperation(value = "是否还有下一个菜单项", notes = "是否还有下一个菜单项")
    public boolean hasNext() {
        if (position >= menuItems.length || menuItems[position] == null) {
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    @ApiOperation(value = "删除菜单项", notes = "删除菜单项")
    public void remove() {
        if (position <= 0) {
            throw new IllegalStateException
                ("You can't remove an item until you've done at least one next()");
        }
        if (menuItems[position-1] != null) {
            for (int i = position-1; i < (menuItems.length-1); i++) {
                menuItems[i] = menuItems[i+1];
            }
            menuItems[menuItems.length-1] = null;
        }
    }

}
