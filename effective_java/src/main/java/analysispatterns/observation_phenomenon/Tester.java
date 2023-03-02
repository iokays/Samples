package analysispatterns.observation_phenomenon;

import analysispatterns.quantity.Length;
import org.junit.jupiter.api.Test;

public class Tester {

    private final Person johnSmith = new Person("John Smith");
    private final PhenomenonType height = new PhenomenonType("height");
    private final PhenomenonType weight = new PhenomenonType("weight");
    private final PhenomenonType blood = new PhenomenonType("blood");

    @Test
    public void test() {
        new Measurement(johnSmith, height, Length.metric(1.78));
        new CategoryObservation(johnSmith, new Phenomenon("gain", weight));
        System.out.println(johnSmith);
    }

}
