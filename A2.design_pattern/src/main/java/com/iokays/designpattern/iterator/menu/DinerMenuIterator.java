package com.iokays.designpattern.iterator.menu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

import java.util.Iterator;

@ApiModel(value = "餐厅菜单迭代器")
public class DinerMenuIterator implements Iterator<MenuItem> {

    @ApiModelProperty(value = "菜单项")
    MenuItem[] items;

    @ApiModelProperty(value = "菜单项位置")
    int position = 0;

    public DinerMenuIterator(MenuItem[] items) {
        this.items = items;
    }

    @Override
    @ApiOperation(value = "获取下一个菜单项", notes = "获取下一个菜单项")
    public MenuItem next() {
        MenuItem menuItem = items[position];
        position = position + 1;
        return menuItem;
    }

    @ApiOperation(value = "是否还有下一个菜单项", notes = "是否还有下一个菜单项")
    public boolean hasNext() {
        if (position >= items.length || items[position] == null) {
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
        if (items[position-1] != null) {
            for (int i = position-1; i < (items.length-1); i++) {
                items[i] = items[i+1];
            }
            items[items.length-1] = null;
        }
    }
}
