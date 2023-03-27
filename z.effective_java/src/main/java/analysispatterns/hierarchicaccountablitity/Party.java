package analysispatterns.hierarchicaccountablitity;

import analysispatterns.name.NamedObject;
import com.google.common.collect.Sets;

import java.util.Set;
import java.util.stream.Collectors;

public class Party extends NamedObject {

    private PartyType type;

    private final Set<Accountability> parentAccountabilities = Sets.newHashSet();

    private final Set<Accountability> childrenAccountabilities = Sets.newHashSet();

    public Party(String name, PartyType type) {
        super(name);
        this.type = type;
    }

    public PartyType type() {
        return this.type;
    }

    void friendAddChildAccontabilities(Accountability arg) {
        this.childrenAccountabilities.add(arg);
    }

    boolean ancestorsInclude(Party sample, AccountabilityType type) {
        return this.parents(type).stream().filter(v -> v.equals(sample) || v.ancestorsInclude(sample, type)).findFirst().isPresent();
    }

    void friendAddParentAccontabilities(Accountability arg) {
        this.parentAccountabilities.add(arg);
    }

    Set<Party> parents() {
        return this.parentAccountabilities.stream().map(Accountability::parent).collect(Collectors.toSet());
    }

    Set<Party> parents(final AccountabilityType arg) {
        return this.parentAccountabilities.stream().filter(v -> v.type().equals(arg)).map(Accountability::parent).collect(Collectors.toSet());
    }

    Set<Party> children() {
        return this.childrenAccountabilities.stream().map(Accountability::child).collect(Collectors.toSet());
    }




}
