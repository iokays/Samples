package com.iokays.sample.builder.hierarchy;

/**
 * 半圆形烤乳酪馅饼
 * 有一个可选参数：酱汁是否在里面
 */
public class Calzone extends Pizza {
    private final boolean sauceInside;

    public static class Builder extends Pizza.Builder<Builder> {
        private boolean sauceInside = false; // Default

        public Builder sauceInside() {
            sauceInside = true;
            return this;
        }

        @Override
        public Calzone build() {
            return new Calzone(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    private Calzone(Builder builder) {
        super(builder);
        sauceInside = builder.sauceInside;
    }

    @Override
    public String toString() {
        return "Calzone " + (sauceInside ? "(sauce inside)" : "(sauce outside)") + " " + super.toString();
    }
}
