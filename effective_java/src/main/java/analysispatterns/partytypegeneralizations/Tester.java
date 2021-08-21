package analysispatterns.partytypegeneralizations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Tester {

    private final static PartyType gp = new PartyType("General Practitioner");
    private final static PartyType pediatrician = new PartyType("Pediatrician");
    private final static PartyType gpAndPediatrician = new PartyType("GP/Pediatrician", gp, pediatrician);

    private final static PartyType patient = new PartyType("Patient");

    private final static ConnectionRuleAccountabilityType appointment = new ConnectionRuleAccountabilityType("appointment");

    private static Party edwards;
    private static Party mark;
    private static Party stMarys;
    private static Party tom;

    @BeforeAll
    public static void setUp() {
        appointment.addConnectionRule(gpAndPediatrician, patient);
        edwards = new Party("edwards", gpAndPediatrician);
        mark = new Party("mark", gp);
        stMarys = new Party("St Mary's", pediatrician);
        tom = new Party("tom", patient);
    }

    @Test
    public void test() {
        Accountability.create(edwards, tom, appointment);
        Accountability.create(mark, tom, appointment);
        Accountability.create(stMarys, tom, appointment);

        Assertions.assertTrue(tom.parents().contains(edwards));
        Assertions.assertTrue(tom.parents().contains(mark));
        Assertions.assertTrue(tom.parents().contains(stMarys));
    }

}
