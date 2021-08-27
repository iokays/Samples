package analysispatterns.effectivity;

import analysispatterns.dualtimerecord.MfDate;
import analysispatterns.range.DateRange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class Tester {

    private Person duke = new Person("duke");
    private Company india = new Company("india");
    private Company peninsular = new Company("peninsular");
    private Company dublin = new Company("dublin");

    @BeforeEach
    public void setUp() {
        duke.addEmployment(india, new MfDate(LocalDate.of(1999, 12, 1)));
        duke.addEmployment(peninsular, new MfDate(LocalDate.of(2000, 4, 1)));
        duke.employments().get(0).end(new MfDate(LocalDate.of(2000, 5, 1)));
    }

    @Test
    public void testAdditive() {
        Assertions.assertEquals(2, duke.employments().size());

        var effectiveDate = new MfDate(LocalDate.of(2000, 6, 1));
        var actual = duke.employments().stream().filter(v -> v.isEffectiveOn(effectiveDate)).findAny().get();
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(peninsular, actual.company());
    }

    @Test
    public void testRetro() {
        duke.employments().get(1).effective(DateRange.startingOn(new MfDate(LocalDate.of(2000, 6, 1))));
        duke.addEmployment(new Employment(dublin, new DateRange(new MfDate(2000,5,1), new MfDate(2000,5,31))));

        var april = duke.employments().stream().filter(v -> v.isEffectiveOn(new MfDate(2000, 4, 10))).findAny().get();
        Assertions.assertNotNull(april);
        Assertions.assertEquals(india, april.company());

        var may = duke.employments().stream().filter(v -> v.isEffectiveOn(new MfDate(2000, 5, 10))).findAny().get();
        Assertions.assertNotNull(may);
        Assertions.assertEquals(dublin, may.company());

    }



}
