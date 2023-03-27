package analysispatterns.measurement;

import analysispatterns.quantity.Length;
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

        this.person.addMeasurement(this);

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Measurement{");
        sb.append("person=").append(person);
        sb.append(", phenomenonType=").append(phenomenonType);
        sb.append(", length=").append(quantity);
        sb.append('}');
        return sb.toString();
    }
}
