package analysispatterns.observation_phenomenon;

import analysispatterns.name.NamedObject;
import analysispatterns.quantity.Quantity;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

public class Person extends NamedObject {

    private List<Observation> observations;

    public Person(String name) {
        super(name);
        this.observations = Lists.newArrayList();
    }

    public void addObservation(final Observation observation) {
        if (null == this.observations) {
            this.observations = Lists.newArrayList();
        }

        this.observations.add(observation);
    }

    public Quantity amountOf(final PhenomenonType phenomenonType) {
        final Measurement result = this.valueOf(phenomenonType);
        if (null == result) {
            return null;
        }
        return result.quantity();
    }

    public Measurement valueOf(final PhenomenonType phenomenonType) {
        if (CollectionUtils.isNotEmpty(this.observations)) {
            for (Observation observation : this.observations) {
                if (observation instanceof Measurement) {
                    Measurement measurement = (Measurement) observation;
                    if (measurement.phenomenonType().equals(phenomenonType)) {
                        return (Measurement) observation;
                    }
                }
            }
        }
        return null;
    }


}
