package analysispatterns.accountability;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class Tester {

    AccountabilityType supervision = new AccountabilityType("Supervises");

    AccountabilityType appointment = new AccountabilityType("Appointment");

    Party mark = new Party("mark");
    Party tom = new Party("tom");
    Party stMarys= new Party("St Mary's");

    @BeforeAll
    public void setUp() {
        new Accountability(stMarys, mark, appointment);
        new Accountability(stMarys, tom, appointment);
    }

    @Test
    public void testSimple() {
        Assertions.assertTrue(stMarys.children().contains(mark));
        Assertions.assertTrue(mark.parents().contains(stMarys));
    }

    @Test
    public void testParents() {
        Accountability.create(tom, mark, supervision);
        Assertions.assertTrue(mark.parents().contains(stMarys));
        Assertions.assertTrue(mark.parents(appointment).contains(stMarys));

        Assertions.assertEquals(2, mark.parents().size());
        Assertions.assertEquals(1, mark.parents(appointment).size());
        Assertions.assertEquals(1, mark.parents(supervision).size());

        Assertions.assertTrue(mark.parents(supervision).contains(tom));
    }

}
