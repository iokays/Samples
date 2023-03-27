package analysispatterns.transactions;

import analysispatterns.quantity.Money;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Entry implements Serializable {

    private Money amount;

    private LocalDateTime date;

    private Account account;

    private Transaction transaction;

    public Entry(Money amount, LocalDateTime date, Account account, Transaction transaction) {
        super();
        this.amount = amount;
        this.date = date;
        this.account = account;
        this.transaction = transaction;
    }


}
