package analysispatterns.party;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Tester {

    @Test
    public void test() {
        final var party = SimplePartyFactory.create("person", "andy", "17620393016", "china", "592721086@qq.com");
        Assertions.assertTrue(party instanceof Person);
    }

}
