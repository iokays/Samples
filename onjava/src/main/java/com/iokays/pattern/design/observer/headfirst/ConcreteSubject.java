package com.iokays.pattern.design.observer.headfirst;

import com.google.common.collect.Lists;

import java.util.List;

public class ConcreteSubject implements Subject {

    private List<Observer> observers;

    public ConcreteSubject() {
        this.observers = Lists.newArrayList();
    }

    @Override
    public void registerObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObserves() {
        this.observers.forEach(Observer::update);
    }
}
