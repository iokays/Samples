package analysispatterns.observation_phenomenon;

public class CategoryObservation extends Observation {

    private Phenomenon phenomenon;

    public CategoryObservation(Person person, Phenomenon phenomenon) {
        super(person);
        this.phenomenon = phenomenon;
    }
}
