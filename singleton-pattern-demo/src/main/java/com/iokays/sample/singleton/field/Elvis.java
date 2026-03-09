package com.iokays.sample.singleton.field;

public class Elvis {
    public static final Elvis INSTANCE = new Elvis();
    
    private Elvis() {
        if (INSTANCE != null) {
            throw new IllegalStateException("Singleton already initialized");
        }
    }
    
    public void leaveTheBuilding() {
        System.out.println("Elvis is leaving the building");
    }
    
    @Override
    public String toString() {
        return "Elvis{singleton instance}";
    }
}
