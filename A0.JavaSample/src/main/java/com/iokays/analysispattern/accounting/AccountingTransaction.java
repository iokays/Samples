package com.iokays.analysispattern.accounting;

import com.google.common.collect.Sets;
import com.iokays.analysispattern.base.Money;
import com.iokays.analysispattern.exception.ImmutableTransactionException;
import com.iokays.analysispattern.exception.UnableToPostException;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

public class AccountingTransaction implements Serializable {

    private final LocalDate date;

    private final Collection<Entry> entries = Sets.newHashSet();

    private boolean wasPosted = false;

    public AccountingTransaction(LocalDate date) {
        this.date = date;
    }

    public void add(Money amount, Account account) {
        if (wasPosted) {
            throw new ImmutableTransactionException("cannot add entry to a transaction that's already posted");
        }
        entries.add(new Entry(amount, date, account, this));
    }

    public void post() {
        if (!canPost()) {
            throw new UnableToPostException();
        }
        entries.forEach(Entry::post);
        wasPosted = true;
    }

    public boolean canPost() {
        return balance().isZero();
    }

    private Money balance() {
        return entries.stream().map(Entry::amount).reduce(Money.dollars(0), Money::add);
    }


}
