package com.iokays.sample;

import org.jspecify.annotations.NullMarked;

@NullMarked
public class App {

    static void main(String[] args) {
        System.out.println("Hello World!");
        Integer a = 10;
        a = null; //Non-null except locals
    }
}
