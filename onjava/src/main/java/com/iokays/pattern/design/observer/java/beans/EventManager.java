package com.iokays.pattern.design.observer.java.beans;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class EventManager {
    PropertyChangeSupport PCS = new PropertyChangeSupport(this);


    //add listener
    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        PCS.addPropertyChangeListener(pcl);
    }

    //Trigger property change events
    protected void triggerPropertyChange(String propertyName, Object oldValue, Object newValue) {
        PCS.firePropertyChange(propertyName, oldValue, newValue);
    }

    //remove listener
    protected void removePropertyChange(PropertyChangeListener pcs) {
        PCS.removePropertyChangeListener(pcs);
    }
}