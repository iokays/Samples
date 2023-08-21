package com.iokays.pattern.analysis.summaryaccount;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.List;

public class DetailAccount extends Account {

    private final List<Entry> entries;

    public DetailAccount() {
        this.entries = Lists.newArrayList();
    }


    @Override
    public void addEntry(final Entry entry) {
        this.entries.add(entry);
    }

    @Override
    public BigDecimal balance() {
        return this.entries.stream().map(Entry::amount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
