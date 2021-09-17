package analysispatterns.observation;

import analysispatterns.quantity.Quantity;

public class Measurement extends Observation {

    private Quantity quantity;

    public Measurement(Person person, PhenomenonType phenomenonType, final Quantity quantity) {
        super(person, phenomenonType);
        this.quantity = quantity;
    }

    public Quantity quantity() {
        return quantity;
    }
}
