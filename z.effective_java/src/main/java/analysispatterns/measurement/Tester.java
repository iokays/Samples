package analysispatterns.measurement;

import analysispatterns.quantity.Length;
import analysispatterns.quantity.Quality;
import org.junit.jupiter.api.Test;

public class Tester {

    private final Person johnSmith = new Person("John Smith");
    private final PhenomenonType height = new PhenomenonType("height");
    private final PhenomenonType weight = new PhenomenonType("weight");

    @Test
    public void test() {

        new Measurement(johnSmith, height, Length.metric(1.8));
        new Measurement(johnSmith, weight, Quality.metric(60));

        System.out.println(johnSmith);

    }

}


