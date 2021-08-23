package analysispatterns.hierarchicaccountablitity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Tester {

    private PartyType nation = new PartyType("nation");
    private PartyType state = new PartyType("state");
    private PartyType county = new PartyType("county");
    private PartyType city = new PartyType("city");

    private Party usa, ma, nh, middlesex, melrose;

    private final LevelledAccountabilityType region = new LevelledAccountabilityType("region");

    @BeforeEach
    public void setUp() {
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

    @Test
    public void testReversedLevels() {
        try {
            Accountability.create(ma, usa, region);
            Assertions.fail();
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
    }

    @Test
    public void testSameLevels() {
        try {
            Accountability.create(ma, nh, region);
            Assertions.fail();
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
    }

    @Test
    public void testSkipLevels() {
        try {
            Accountability.create(ma, melrose, region);
            Assertions.fail();
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }

    }


}
