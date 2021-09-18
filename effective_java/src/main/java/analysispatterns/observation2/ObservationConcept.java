package analysispatterns.observation2;

import analysispatterns.name.NamedObject;
import edu.princeton.cs.algs4.Bag;

import java.util.Arrays;

public abstract class ObservationConcept extends NamedObject {

    private Bag<ObservationConcept> supertypes;

    public ObservationConcept(String name) {
        super(name);
    }
    public ObservationConcept(String name, ObservationConcept supertype) {
        super(name);
        this.addSupertype(supertype);
    }

    public ObservationConcept(String name, ObservationConcept supertype, ObservationConcept... supertypes) {
        this(name, supertype);
        if (null != supertypes && supertypes.length > 0) {
            Arrays.stream(supertypes).forEach(v -> this.addSupertype(v));
        }
    }

    public void addSupertype(final ObservationConcept observationConcept) {
        if (null == this.supertypes) {
            this.supertypes = new Bag<>();
        }

        this.supertypes.add(observationConcept);
    }

}
