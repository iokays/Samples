package com.iokays.pattern.analysis.summaryaccount;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.List;

public class SummaryAccount extends Account {

    private List<Account> accounts;

    public SummaryAccount(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public BigDecimal balance() {
        return accounts.stream().map(Account::balance).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
