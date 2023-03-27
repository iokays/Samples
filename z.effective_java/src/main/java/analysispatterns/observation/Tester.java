package analysispatterns.observation;

import analysispatterns.quantity.Length;
import analysispatterns.quantity.Quality;
import org.junit.jupiter.api.Test;

public class Tester {

    private final Person johnSmith = new Person("John Smith");
    private final PhenomenonType height = new PhenomenonType("height");
    private final PhenomenonType weight = new PhenomenonType("weight");
    private final PhenomenonType blood = new PhenomenonType("blood");

    @Test
    public void test() {
        new CategoryObservation(johnSmith, height, new Category("高"));
        new Measurement(johnSmith, height, Length.metric(1.86));
        new Measurement(johnSmith, weight, Quality.metric(60));
        new CategoryObservation(johnSmith, blood, new Category("A"));

        System.out.println(johnSmith);
    }

}
