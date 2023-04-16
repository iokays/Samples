package com.iokays.pattern.analysis.transaction;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.List;

public class Account {
    private final List<Entry> entries;

    public Account() {
        super();
        this.entries = Lists.newArrayList();
    }

    public void addEntry(final Entry entry) {
        this.entries.add(entry);
    }

    public BigDecimal balance() {
        return this.entries.stream().map(Entry::amount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
