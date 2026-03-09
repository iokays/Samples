package com.iokays.sample.builder.hierarchy;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

/**
 * 抽象类 Pizza，展示类层次结构中的 Builder 模式
 * 
 * 使用递归类型参数（recursive type parameter）和抽象的 self 方法，
 * 允许方法链在子类中正常工作，不需要强制转换。
 */
public abstract class Pizza {
    public enum Topping {HAM, MUSHROOM, ONION, PEPPER, SAUSAGE}
    
    final Set<Topping> toppings;

    abstract static class Builder<T extends Builder<T>> {
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);
        
        public T addTopping(Topping topping) {
            toppings.add(Objects.requireNonNull(topping));
            return self();
        }

        abstract Pizza build();

        // Subclasses must override this method to return "this"
        protected abstract T self();
    }

    Pizza(Builder<?> builder) {
        toppings = builder.toppings.clone(); // See Item 50
    }

    @Override
    public String toString() {
        return "Pizza with toppings: " + toppings;
    }
}
