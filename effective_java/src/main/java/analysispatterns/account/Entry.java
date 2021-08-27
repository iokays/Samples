package analysispatterns.account;

import analysispatterns.dualtimerecord.TimePoint;
import analysispatterns.quantity.Money;

import java.io.Serializable;

public class Entry implements Serializable {

    private Money quantity;

    private TimePoint whenCharged;

    private TimePoint whenBooked;

    public Entry(Money quantity, TimePoint whenBooked) {
        this.quantity = quantity;
        this.whenBooked = whenBooked;
        this.whenCharged = TimePoint.now();
    }
}
