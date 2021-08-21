package analysispatterns.hierarchicaccountablitity;

import org.junit.jupiter.api.BeforeAll;

public class Tester {

    private final PartyType nation = new PartyType("nation");
    private final PartyType state = new PartyType("state");
    private final PartyType county = new PartyType("county");
    private final PartyType city = new PartyType("city");

    private Party usa, ma, nh, middlesex, melrose;

    private LevelledAccountabilityType region = new LevelledAccountabilityType();

    @BeforeAll
    public void setUp() {
        final PartyType[] levels = {nation, state, county, city};

        usa = new Party("uas", nation);
        ma = new Party("ma", state);
        nh = new Party("nh", state);


    }


}
