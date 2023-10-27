package com.iokays.analysispattern.accountabilityknowledgelevel;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * 关联责任类型
 */
public class ConnectionAccountabilityType extends AccountabilityType {

    final Set<ConnectionRule> connectionRules = Sets.newHashSet();

    public ConnectionAccountabilityType(String name) {
        super(name);
    }

    @Override
    protected boolean areValidPartyTypes(Party parent, Party child) {
        return connectionRules.stream().anyMatch(rule -> rule.isVaild(parent, child));
    }


    public void addConnectionRule(PartyType parent, PartyType child) {
        connectionRules.add(new ConnectionRule(parent, child));
    }

}
