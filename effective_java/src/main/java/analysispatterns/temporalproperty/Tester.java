package analysispatterns.temporalproperty;

import analysispatterns.dualtimerecord.MfDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Tester {

    private Customer martin;

    private Address franklin = new Address("961 Franklin St");
    private Address worcester = new Address("88 Worcester St");

    private MfDate jul1 = new MfDate(1996, 7, 1);
    private MfDate jul15 = new MfDate(1996, 7, 15);
    private MfDate aug1 = new MfDate(1996, 8, 1);
    private MfDate aug10 = new MfDate(1996, 8, 10);

    @BeforeEach
    public void setUp() {
        MfDate.setToday(new MfDate(1996, 1, 1));
        martin = new Customer("Martin");
        martin.putAddress(new MfDate(1994, 3,1), worcester);
        MfDate.setToday(1996, 8, 10);
        martin.putAddress(new MfDate(1996, 7, 4), franklin);
        MfDate.setToday(2000, 9, 11);
    }

    @Test
    public void testSimpleBitemporal() {
        System.out.println(martin);
        Assertions.assertEquals(worcester, martin.getAddress(jul1, aug1), "jul1 as at aug 1");
        Assertions.assertEquals(worcester, martin.getAddress(jul1, aug10), "jul1 as at aug 10");
        Assertions.assertEquals(worcester, martin.getAddress(jul1), "jul1 as at now");

        Assertions.assertEquals(worcester, martin.getAddress(jul15, aug1), "jul15 as at aug 1");
        Assertions.assertEquals(franklin, martin.getAddress(jul15, aug10), "jul15 as at aug 10");
        Assertions.assertEquals(franklin, martin.getAddress(jul15), "jul15 as at now");

    }



}
