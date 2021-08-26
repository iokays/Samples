package analysispatterns.account;

import analysispatterns.dualtimerecord.LocalDateTimePoint;
import analysispatterns.quantity.Money;

import java.io.Serializable;

public class Entry implements Serializable {

    private Money quantity;

    private LocalDateTimePoint whenCharged;

    private LocalDateTimePoint whenBooked;

    public Entry(Money quantity, LocalDateTimePoint whenBooked) {
        this.quantity = quantity;
        this.whenBooked = whenBooked;
        this.whenCharged = LocalDateTimePoint.now();
    }
}
