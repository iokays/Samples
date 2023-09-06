package com.iokays.analysispattern.accountability;

import com.google.common.collect.Sets;
import com.iokays.analysispattern.base.NamedObject;

import java.util.Set;
import java.util.stream.Collectors;

public class Party extends NamedObject {

    private final Set<Accountability> parentAccountabilities = Sets.newHashSet();
    private final Set<Accountability> childAccountabilities =  Sets.newHashSet();

    public Party(String name) {
        super(name);
    }

    public Set<Party> parents() {
        return this.parentAccountabilities.stream().map(Accountability::parent).collect(Collectors.toSet());
    }

    public Set<Party> children() {
        return this.childAccountabilities.stream().map(Accountability::child).collect(Collectors.toSet());
    }

    public boolean ancestorsInclude(Party party, AccountabilityType type) {
        return this.parents().stream().anyMatch(parent -> parent.equals(party) || parent.ancestorsInclude(party, type));
    }

    public void friendAddChildAccountability(Accountability arg) {
        childAccountabilities.add(arg);
    }
    public void friendAddParentAccountability(Accountability arg) {
        parentAccountabilities.add(arg);
    }

}
