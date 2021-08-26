package analysispatterns.transactions;

import analysispatterns.quantity.Money;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;

public class Transaction implements Serializable {

    private final Collection<Entry> entries = new HashSet();

    public Transaction(Money money, Account from, Account to, LocalDateTime date) {


    }
}
