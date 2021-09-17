package analysispatterns.account;

import analysispatterns.dualtimerecord.MfDate;
import analysispatterns.quantity.Money;

import java.io.Serializable;

public class Entry implements Serializable {

    private Money quantity;

    private MfDate whenCharged;

    private MfDate whenBooked;

    public Entry(Money quantity, MfDate whenBooked) {
        this.quantity = quantity;
        this.whenBooked = whenBooked;
        this.whenCharged = MfDate.today();
    }
}
