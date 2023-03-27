package analysispatterns.observation_phenomenon;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

public abstract class Observation implements Serializable {

    private Person person;

    public Observation(Person person) {
        this.person = person;
        this.person.addObservation(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
