package analysispatterns.partytypegeneralizations;

import java.util.HashSet;
import java.util.Set;

public class ConnectionRuleAccountabilityType extends AccountabilityType {

    private final Set<ConnectionRule> connectionRules = new HashSet<>();

    public ConnectionRuleAccountabilityType(String name) {
        super(name);
    }

    public void addConnectionRule(PartyType parent, PartyType child) {
        connectionRules.add(new ConnectionRule(parent, child));
        parent.parents().forEach(v -> connectionRules.add(new ConnectionRule(v, child)));
        child.parents().forEach(v -> connectionRules.add(new ConnectionRule(parent, v)));
    }

    @Override
    protected boolean areValidPartyTypes(final Party party, final Party child) {
        return this.connectionRules.stream().filter(v -> v.isValid(party, child)).findFirst().isPresent();
    }

}
