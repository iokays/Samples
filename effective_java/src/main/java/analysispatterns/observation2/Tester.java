package analysispatterns.observation2;

import analysispatterns.timerecord.MfDate;
import analysispatterns.timerecord.TimePeriod;
import analysispatterns.timerecord.TimeRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class Tester {

    private final Patient johnSmith = new Patient("John Smith");

    private final PhenomenonType height = new PhenomenonType("height");
    private final PhenomenonType weight = new PhenomenonType("weight");
    private final PhenomenonType blood = new PhenomenonType("blood");

    private final PhenomenonType body = new PhenomenonType("body");

    private final Phenomenon A = new Phenomenon("A", blood);
    private final Phenomenon A1 = new Phenomenon("A1", A);
    private final Phenomenon A2 = new Phenomenon("A2", A);

    final Phenomenon diabetes = new Phenomenon("diabetes", body);
    final Phenomenon diabetes1 = new Phenomenon("type I diabetes", diabetes);
    final Phenomenon diabetes2 = new Phenomenon("type II diabetes", diabetes);

    final Phenomenon perniciousAnemia = new Phenomenon("pernicious anemia", body);
    final Phenomenon alcoholAbuse = new Phenomenon("alcohol abuse", body);

    private final MfDate today = MfDate.today();


    @BeforeEach
    public void setUp() {
    }

    @DisplayName("3.6 Samples")
    @Test
    public void testSubtypingObservationConcepts() {
        final TimeRecord applicability = new TimePeriod(new MfDate(1988, 10, 11), MfDate.today());
        new CategoryObservation(johnSmith, A1, applicability, today);

        final TimeRecord applicability2 = new TimePeriod(new MfDate(2021, 01, 01), MfDate.today());
        new CategoryObservation(johnSmith, diabetes1, applicability2, today);
    }

    @DisplayName("3.9 Samples")
    @Test
    public void testRejectedObservation() {
        final MfDate jan = new MfDate(2021, 01, 01);
        final MfDate june = new MfDate(2021, 06, 30);

        final TimeRecord applicability = new TimePeriod(jan, june);

        final Observation observation = new CategoryObservation(johnSmith, perniciousAnemia, new TimePeriod(jan, june), june);

        new CategoryObservation(johnSmith, alcoholAbuse, new TimePeriod(jan, today), today);

        //TODO

    }

    @Test
    public void testObservation() {

    }

}
