package analysispatterns.temporalobject;

import analysispatterns.dualtimerecord.MfDate;
import analysispatterns.quantity.Money;
import analysispatterns.temporalproperty.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Tester {


    private Customer martin;

    private Address damon15 = new Address("15 damon St");

    private MfDate jul1 = new MfDate(1996, 7, 1);
    private MfDate jul15 = new MfDate(1996, 7, 15);
    private MfDate aug1 = new MfDate(1996, 8, 1);
    private MfDate aug10 = new MfDate(1996, 8, 10);

    @BeforeEach
    public void setUp() {
        martin = new Customer("Martin");
    }

    @Test
    public void testSimple () {
        MfDate.setToday(new MfDate (1998, 8, 23));
        martin.setAddress(damon15);
        martin.setCreditLimit(Money.dollars(100));
        MfDate.setToday(new MfDate (2000, 9,30));

        System.out.println(martin);
    }


}
