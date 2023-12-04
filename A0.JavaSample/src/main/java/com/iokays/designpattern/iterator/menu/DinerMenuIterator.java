package com.iokays.designpattern.iterator.menu;


import java.util.Iterator;

/**
 * 餐厅菜单迭代器
 */
public class DinerMenuIterator implements Iterator<MenuItem> {

    /**
     * 菜单项
     */
    MenuItem[] items;

    /**
     * 菜单项位置
     */
    int position = 0;

    public DinerMenuIterator(MenuItem[] items) {
        this.items = items;
    }

    @Override
    /**
     * 获取下一个菜单项", notes = "获取下一个菜单项
     */
    public MenuItem next() {
        MenuItem menuItem = items[position];
        position = position + 1;
        return menuItem;
    }

    /**
     * 是否还有下一个菜单项", notes = "是否还有下一个菜单项
     */
    public boolean hasNext() {
        if (position >= items.length || items[position] == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    /**
     * 删除菜单项", notes = "删除菜单项
     */
    public void remove() {
        if (position <= 0) {
            throw new IllegalStateException
                    ("You can't remove an item until you've done at least one next()");
        }
        if (items[position - 1] != null) {
            for (int i = position - 1; i < (items.length - 1); i++) {
                items[i] = items[i + 1];
            }
            items[items.length - 1] = null;
        }
    }
}
