package analysispatterns.hierarchicaccountablitity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Tester {

    private static final PartyType nation = new PartyType("nation");
    private static final PartyType state = new PartyType("state");
    private static final PartyType county = new PartyType("county");
    private static final PartyType city = new PartyType("city");

    private static Party usa, ma, nh, middlesex, melrose;

    private static final LevelledAccountabilityType region = new LevelledAccountabilityType("region");

    @BeforeEach
    public static void setUp() {
        final PartyType[] levels = {nation, state, county, city};

        usa = new Party("usa", nation);
        ma = new Party("ma", state);
        nh = new Party("nh", state);

        middlesex = new Party("usa", county);
        melrose = new Party("usa", city);

        region.setLevels(levels);

        Accountability.create(usa, ma, region);
        Accountability.create(usa, nh, region);
        Accountability.create(ma, middlesex, region);
        Accountability.create(middlesex, melrose, region);

    }

    @Test
    public void testLevels() {
        Assertions.assertTrue(melrose.ancestorsInclude(ma, region));
    }
    

}
