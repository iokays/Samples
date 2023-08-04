package com.iokays.pattern.analysis.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;

public class Transaction {

    private Collection<Entry> entries = new HashSet<>();

    public Transaction(BigDecimal amount, Account from, Account to, LocalDateTime whenCharged, LocalDateTime whenBooked) {
        Entry fromEntry = new Entry(amount.negate(), whenCharged, whenBooked);
        from.addEntry(fromEntry);
        this.entries.add(fromEntry);

        Entry toEntry = new Entry(amount, whenCharged, whenBooked);
        to.addEntry(toEntry);
        this.entries.add(toEntry);

    }

}
