package com.iokays.sample.core.gen;


import com.querydsl.sql.codegen.DefaultNamingStrategy;

public class DynamicPrefixNamingStrategy extends DefaultNamingStrategy {

    private final String prefixToStrip;

    public DynamicPrefixNamingStrategy(String prefixToStrip) {
        this.prefixToStrip = prefixToStrip;
    }

    @Override
    public String getClassName(String tableName) {
        String stripped = tableName;
        if (tableName.startsWith(prefixToStrip)) {
            stripped = tableName.substring(prefixToStrip.length());
        }
        return super.getClassName(stripped);
    }
}
