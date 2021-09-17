package analysispatterns.protocol;

import org.junit.jupiter.api.Test;


public class Tester {

    @Test
    public void test() {
        final var diabetes = new ObservationConcept("diabetes");
        final var i_diabetes = new ObservationConcept("type I diabetes", diabetes);
        final var ii_diabetes = new ObservationConcept("type II diabetes", diabetes);

        System.out.println(diabetes);
        System.out.println(i_diabetes);
        System.out.println(ii_diabetes);

    }

}
