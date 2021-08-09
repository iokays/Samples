package apsupp.orgainzationstructures.accountability;

import apsupp.NamedObject;
import com.google.common.collect.Sets;

import java.util.Set;
import java.util.stream.Collectors;

public class Party extends NamedObject {

    private final Set<Accountability> parentAccountabilities = Sets.newHashSet();

    private final Set<Accountability> childrenAccountabilities = Sets.newHashSet();

    public Party(String name) {
        super(name);
    }

    void friendAddChildAccontabilities(Accountability arg) {
        this.childrenAccountabilities.add(arg);
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
