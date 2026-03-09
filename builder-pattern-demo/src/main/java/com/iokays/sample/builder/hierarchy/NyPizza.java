package com.iokays.sample.builder.hierarchy;

import java.util.Objects;

/**
 * 纽约风格披萨
 * 有一个必需的尺寸参数
 */
public class NyPizza extends Pizza {
    public enum Size {SMALL, MEDIUM, LARGE}
    
    private final Size size;

    public static class Builder extends Pizza.Builder<Builder> {
        private final Size size;

        public Builder(Size size) {
            this.size = Objects.requireNonNull(size);
        }

        @Override
        public NyPizza build() {
            return new NyPizza(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    private NyPizza(Builder builder) {
        super(builder);
        size = builder.size;
    }

    @Override
    public String toString() {
        return size + " New York Style " + super.toString();
    }
}
