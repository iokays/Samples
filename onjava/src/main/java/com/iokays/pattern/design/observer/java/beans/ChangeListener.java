package com.iokays.pattern.design.observer.java.beans;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ChangeListener implements PropertyChangeListener {
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println(evt.getPropertyName());
        System.out.println("NewValue=" + evt.getNewValue());
        System.out.println("OldValue=" + evt.getOldValue());
        System.out.println(evt.getPropagationId());
        System.out.println("-------------------------");
        Object bean = evt.getSource();
        System.out.println(bean);
    }
}
