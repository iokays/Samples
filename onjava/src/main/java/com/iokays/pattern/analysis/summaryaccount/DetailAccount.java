package com.iokays.pattern.analysis.summaryaccount;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.List;

public class DetailAccount extends Account {

    private final List<Entry> entries;

    public DetailAccount(final Account parent) {
        super(parent);
        this.entries = Lists.newArrayList();
    }

    public void addEntry(final Entry entry) {
        this.entries.add(entry);
    }

    @Override
    public BigDecimal balance() {
        return this.entries.stream().map(Entry::amount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    @Override
    void assertValidParent(Account aParent) {
        Preconditions.checkState(aParent instanceof SummaryAccount, "父级必须为汇总账户");
        super.assertValidParent(aParent);
    }
}
