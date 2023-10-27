package com.iokays.analysispattern.accountabilityknowledgelevel;

import com.iokays.analysispattern.base.NamedObject;

public abstract class AccountabilityType extends NamedObject {

    public AccountabilityType(String name) {
        super(name);
    }

    boolean canCreateAccountability(Party parent, Party child) {
        return areValidPartyTypes(parent, child);
    }

    protected abstract boolean areValidPartyTypes(Party parent, Party child);

}
