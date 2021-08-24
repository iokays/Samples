package analysispatterns.measurement;

import analysispatterns.quantity.Quantity;

import java.io.Serializable;

public class Measurement implements Serializable {

    private Person person;

    private PhenomenonType phenomenonType;

    private Quantity quantity;

    public Measurement(Person person, PhenomenonType phenomenonType, Quantity quantity) {
        this.person = person;
        this.phenomenonType = phenomenonType;
        this.quantity = quantity;
    }
}
