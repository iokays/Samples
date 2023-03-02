package analysispatterns.observation;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

public abstract class Observation implements Serializable {

    private Person person;

    private PhenomenonType phenomenonType;

    public Observation(Person person, PhenomenonType phenomenonType) {
        this.person = person;
        this.person.addObservation(this);
        this.phenomenonType = phenomenonType;
    }

    public PhenomenonType phenomenonType() {
        return phenomenonType;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
