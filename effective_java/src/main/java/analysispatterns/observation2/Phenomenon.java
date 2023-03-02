package analysispatterns.observation2;

public class Phenomenon extends ObservationConcept {

    private PhenomenonType phenomenonType;

    public Phenomenon(String name, PhenomenonType phenomenonType) {
        super(name);
        this.phenomenonType = phenomenonType;
    }

    public Phenomenon(String name, Phenomenon supertype) {
        super(name, supertype);
        this.phenomenonType = supertype.phenomenonType;
    }
}
