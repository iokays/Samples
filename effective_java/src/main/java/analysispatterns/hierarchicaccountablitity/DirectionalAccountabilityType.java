package analysispatterns.hierarchicaccountablitity;

public class DirectionalAccountabilityType extends AccountabilityType {

    public DirectionalAccountabilityType(String name) {
        super(name);
    }



    @Override
    protected boolean areValidPartyTypes(Party party, Party child) {
        return false;
    }
}
