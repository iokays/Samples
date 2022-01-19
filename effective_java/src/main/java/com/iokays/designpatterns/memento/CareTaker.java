package com.iokays.designpatterns.memento;

import com.google.common.collect.Lists;

import java.util.List;

public class CareTaker {

    private List<Memento> mementos = Lists.newArrayList();

    public void add(Memento memento) {
        mementos.add(memento);
    }

    public Memento get(int index) {
        return mementos.get(index);
    }

}
