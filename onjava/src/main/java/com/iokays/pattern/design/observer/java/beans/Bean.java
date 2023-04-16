package com.iokays.pattern.design.observer.java.beans;

public class Bean extends EventManager {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        int oldValue = this.id;
        this.id = id;
        triggerPropertyChange("id", oldValue, id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String oldValue = this.name;
        this.name = name;
        triggerPropertyChange("name", oldValue, name);
    }
}
