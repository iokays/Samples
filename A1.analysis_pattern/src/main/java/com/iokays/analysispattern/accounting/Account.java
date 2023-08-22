package com.iokays.analysispattern.accounting;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import com.iokays.analysispattern.base.Currency;
import com.iokays.analysispattern.base.DateRange;
import com.iokays.analysispattern.base.Money;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

public class Account implements Serializable {

    private Currency currency;

    private final Collection<Entry> entries = Sets.newHashSet();

    public void addEntry(Entry entry) {
        Preconditions.checkState(entry.amount().currency().equals(this.currency));
        entries.add(entry);
    }

    public Money balance(DateRange period) {
        final Money zero = new Money(0, this.currency);
        return entries.stream()
                .filter(entry -> period.includes(entry.date()))
                .map(Entry::amount)
                .reduce(zero, Money::add);
    }

    Money balance(LocalDate date) {
        return balance(DateRange.upTo(date));
    }

    Money balance() {
        return balance(LocalDate.now());
    }

    void withdraw(Money amount, Account target, LocalDate date) {
        AccountingTransaction trans = new AccountingTransaction(date);
        trans.add(amount.negate(), this);
        trans.add(amount, target);
        trans.post();
    }


}
