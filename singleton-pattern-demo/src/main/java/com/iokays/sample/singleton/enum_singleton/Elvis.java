package com.iokays.sample.singleton.enum_singleton;

public enum Elvis {
    INSTANCE;
    
    public void leaveTheBuilding() {
        System.out.println("Elvis is leaving the building");
    }
    
    @Override
    public String toString() {
        return "Elvis{singleton instance}";
    }
}
