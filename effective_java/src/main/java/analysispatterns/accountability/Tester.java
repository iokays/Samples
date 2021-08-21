package analysispatterns.accountability;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Tester {

    AccountabilityType supervision = new AccountabilityType("Supervises");

    AccountabilityType appointment = new AccountabilityType("Appointment");

    Party mark = new Party("mark");
    Party tom = new Party("tom");
    Party stMarys= new Party("St Mary's");

    @Before
    public void setUp() {
        new Accountability(stMarys, mark, appointment);
        new Accountability(stMarys, tom, appointment);
    }

    @Test
    public void testSimple() {
        Assert.assertTrue(stMarys.children().contains(mark));
        Assert.assertTrue(mark.parents().contains(stMarys));
    }

    @Test
    public void testParents() {
        Accountability.create(tom, mark, supervision);
        Assert.assertTrue(mark.parents().contains(stMarys));
        Assert.assertTrue(mark.parents(appointment).contains(stMarys));

        Assert.assertEquals(2, mark.parents().size());
        Assert.assertEquals(1, mark.parents(appointment).size());
        Assert.assertEquals(1, mark.parents(supervision).size());

        Assert.assertTrue(mark.parents(supervision).contains(tom));
    }

}
