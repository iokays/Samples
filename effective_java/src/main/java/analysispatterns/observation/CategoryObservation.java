package analysispatterns.observation;

public class CategoryObservation extends Observation {

    private Category category;

    public CategoryObservation(Person person, PhenomenonType phenomenonType, Category category) {
        super(person, phenomenonType);
        this.category = category;
    }
}
