package analysispatterns.observation_phenomenon;

import analysispatterns.name.NamedObject;

public class Phenomenon extends NamedObject {

    public PhenomenonType phenomenonType;

    public Phenomenon(String name, PhenomenonType phenomenonType) {
        super(name);
        this.phenomenonType = phenomenonType;
    }
}
