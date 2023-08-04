package com.iokays.pattern.analysis.summaryaccount;

import com.google.common.collect.Sets;

import java.math.BigDecimal;
import java.util.Set;

public abstract class Account {


    public void addEntry(final Entry entry) {
        throw new UnsupportedOperationException();
    }

    public BigDecimal balance() {
        throw new UnsupportedOperationException();
    }
}
