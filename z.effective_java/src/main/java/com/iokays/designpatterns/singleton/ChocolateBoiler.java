package com.iokays.designpatterns.singleton;

public class ChocolateBoiler {

    private volatile static ChocolateBoiler chocolateBoiler;

    private boolean empty;
    private boolean boiled;

    private ChocolateBoiler() {
        this.empty = true;
        this.boiled = false;
    }

    public static ChocolateBoiler getInstance() {
        if (chocolateBoiler == null) {
            synchronized (ChocolateBoiler.class) {
                if (chocolateBoiler == null) {
                    chocolateBoiler = new ChocolateBoiler();
                }
            }
        }
        return chocolateBoiler;
    }

    public void fill() {
        if (isEmpty()) {
            empty = false;
            boiled = false;
        }
    }

    public void drain() {
        if (!isEmpty() && isBoiled()) {
            empty = true;
        }
    }

    public void boil() {
        if (!isEmpty() && !isBoiled()) {
            boiled = true;
        }
    }



    public boolean isEmpty() {
        return empty;
    }

    public boolean isBoiled() {
        return boiled;
    }
}
