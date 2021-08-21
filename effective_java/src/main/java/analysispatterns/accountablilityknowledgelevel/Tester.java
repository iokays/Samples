package analysispatterns.accountablilityknowledgelevel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Tester {

    private PartyType hospital = new PartyType("Hospital");
    private PartyType doctor = new PartyType("Doctor");
    private PartyType patient = new PartyType("Patient");
    private PartyType consultant = new PartyType("consultant");

    private ConnectionRuleAccountabilityType appointment = new ConnectionRuleAccountabilityType("appointment");

    private ConnectionRuleAccountabilityType supervision = new ConnectionRuleAccountabilityType("supervision");

    private Party mark;
    private Party tom;
    private Party stMarys;

    @Before
    public void setUp() {
        appointment.addConnectionRule(hospital, doctor);
        appointment.addConnectionRule(hospital, consultant);

        supervision.addConnectionRule(doctor, doctor);
        supervision.addConnectionRule(consultant, doctor);
        supervision.addConnectionRule(consultant, consultant);

        mark = new Party("mark", consultant);
        tom = new Party("tom", consultant);
        stMarys = new Party("St Mary's", hospital);
    }

    @Test
    public void testNoConnectionRule() {
        try {
            Accountability.create(mark, stMarys, appointment);
            Assert.fail("created accountability without connection rule");
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
        assert(!stMarys.parents().contains(mark)); // am I paranoid?
    }

}
