package apsupp.orgainzationstructures.accountability;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class Tester {

    AccountabilityTimePeriod timePeriod = new AccountabilityTimePeriod(LocalDate.of(2021, 01, 01), LocalDate.of(2021, 12, 31));

    AccountabilityType supervision = new AccountabilityType("Supervises");

    AccountabilityType appointment = new AccountabilityType("Appointment");

    Party mark = new Party("mark");
    Party tom = new Party("tom");
    Party stMarys= new Party("St Mary's");

    @Before
    public void setUp() {
        new Accountability(stMarys, mark, appointment, timePeriod);
        new Accountability(stMarys, tom, appointment, timePeriod);
    }

    @Test
    public void testSimple() {
        Assert.assertTrue(stMarys.children().contains(mark));
        Assert.assertTrue(mark.parents().contains(stMarys));
    }

    @Test
    public void testParents() {
        Accountability.create(tom, mark, supervision, timePeriod);
        Assert.assertTrue(mark.parents().contains(stMarys));
        Assert.assertTrue(mark.parents(appointment).contains(stMarys));

        Assert.assertEquals(2, mark.parents().size());
        Assert.assertEquals(1, mark.parents(appointment).size());
        Assert.assertEquals(1, mark.parents(supervision).size());

        Assert.assertTrue(mark.parents(supervision).contains(tom));
    }

}
