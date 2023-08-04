package com.iokays.pattern.analysis.summaryaccount;

import java.math.BigDecimal;

public class SummaryAccount extends Account {
    public SummaryAccount(Account parent) {
        super(parent);
    }

    @Override
    public BigDecimal balance() {
        return this.getChildren().stream().map(Account::balance).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
