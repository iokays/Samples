package analysispatterns.protocol;

import analysispatterns.quantity.Quantity;

public class Measurement extends Observation {

    private Quantity quantity;

    private PhenomenonType phenomenonType;


    public Measurement(Protocol protocol, Quantity quantity, PhenomenonType phenomenonType) {
        super(protocol);
        this.quantity = quantity;
        this.phenomenonType = phenomenonType;
    }
}
