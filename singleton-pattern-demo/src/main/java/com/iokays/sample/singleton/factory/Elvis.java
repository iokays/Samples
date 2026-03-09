package com.iokays.sample.singleton.factory;

import java.io.Serializable;

public class Elvis implements Serializable {
    private static final Elvis INSTANCE = new Elvis();
    
    private Elvis() {
        if (INSTANCE != null) {
            throw new IllegalStateException("Singleton already initialized");
        }
    }
    
    public static Elvis getInstance() {
        return INSTANCE;
    }
    
    public void leaveTheBuilding() {
        System.out.println("Elvis is leaving the building");
    }
    
    private Object readResolve() {
        return INSTANCE;
    }
    
    @Override
    public String toString() {
        return "Elvis{singleton instance}";
    }
}
