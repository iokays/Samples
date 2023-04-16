package com.iokays.pattern.design.observer.java.beans;

public class Test {
    public static void main(String args[]) {
        Bean bean = new Bean();
        //把监听器注册到这个bean
        bean.addPropertyChangeListener(new ChangeListener());
        bean.setId(1111);
        bean.setName("gary");

        bean.setId(1112);
    }
}