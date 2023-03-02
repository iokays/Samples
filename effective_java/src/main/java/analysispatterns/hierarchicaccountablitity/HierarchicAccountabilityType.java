package analysispatterns.hierarchicaccountablitity;

public class HierarchicAccountabilityType extends AccountabilityType {

    public HierarchicAccountabilityType(String name) {
        super(name);
    }

    @Override
    protected boolean areValidPartyTypes(Party party, Party child) {
        return false;
    }
}
