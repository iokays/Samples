package com.iokays.analysispattern.accounting;

import com.iokays.analysispattern.base.Money;

import java.time.LocalDate;

public class Entry {
    private Money amount;
    private LocalDate date;
    private Account account;
    private AccountingTransaction transaction;

    Entry(Money amount, LocalDate date, Account account, AccountingTransaction transaction) {
        // only used by AccountingTransaction
        this.amount = amount;
        this.date = date;
        this.account = account;
        this.transaction = transaction;
    }

    public Money amount() {
        return amount;
    }

    public LocalDate date() {
        return date;
    }

    public Account account() {
        return account;
    }

    public AccountingTransaction transaction() {
        return transaction;
    }

    void post() {
        // only used by AccountingTransaction
        account.addEntry(this);
    }

}
