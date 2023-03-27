package analysispatterns.observation_phenomenon;

import analysispatterns.quantity.Quantity;

public class Measurement extends Observation {

    private Quantity quantity;

    private PhenomenonType phenomenonType;

    public Measurement(Person person, PhenomenonType phenomenonType, Quantity quantity) {
        super(person);
        this.phenomenonType = phenomenonType;
        this.quantity = quantity;
    }

    public PhenomenonType phenomenonType() {
        return phenomenonType;
    }

    public Quantity quantity() {
        return quantity;
    }
}
