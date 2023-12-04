package com.iokays.analysispattern.accounting;

import com.iokays.analysispattern.base.Money;

import java.io.Serializable;
import java.time.LocalDateTime;

public class AccountingEntry implements Serializable {

    private final Account account;

    private final Money amount;

    private final LocalDateTime whenBooked;

    public AccountingEntry(Account account, Money amount, LocalDateTime whenBooked) {
        this.account = account;
        this.amount = amount;
        this.whenBooked = whenBooked;
    }

    public Account getAccount() {
        return account;
    }

    public Money getAmount() {
        return amount;
    }

    public LocalDateTime getWhenBooked() {
        return whenBooked;
    }
}
