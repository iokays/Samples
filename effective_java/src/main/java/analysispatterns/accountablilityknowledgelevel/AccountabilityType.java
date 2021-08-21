package analysispatterns.accountablilityknowledgelevel;

import analysispatterns.NamedObject;

public abstract class AccountabilityType extends NamedObject {

    public AccountabilityType(String name) {
        super(name);
    }

    boolean canCreateAccountability(Party party, Party child) {
        return this.areValidPartyTypes(party, child);
    }

    abstract protected boolean areValidPartyTypes(final Party party, final Party child);

}
