package analysispatterns.observation2;

import analysispatterns.name.NamedObject;
import analysispatterns.quantity.Quantity;
import com.google.common.collect.Lists;

import java.util.List;

public abstract class ObjectOfCare extends NamedObject {

    private List<Observation> observations;

    public ObjectOfCare(String name) {
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
        //TODO
        return null;
    }

    public Measurement valueOf(final PhenomenonType phenomenonType) {
        //TODO
        return null;
    }
}
