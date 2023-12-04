package com.iokays.analysispattern.accountabilityknowledgelevel;

public class Accountability {

    final Party parent;
    final Party child;

    final AccountabilityType type;

    private Accountability(Party parent, Party child, AccountabilityType type) {
        this.parent = parent;
        parent.friendAddChildAccountability(this);
        this.child = child;
        child.friendAddParentAccountability(this);
        this.type = type;
    }

    static Accountability create(Party parent, Party child, AccountabilityType type) {
        if (!canCreate(parent, child, type)) {
            throw new IllegalArgumentException("Invalid Accountability");
        }
        return new Accountability(parent, child, type);
    }

    static boolean canCreate(Party parent, Party child, AccountabilityType type) {
        return !parent.equals(child)
                && !parent.ancestorsInclude(child, type)
                && type.canCreateAccountability(parent, child);
    }

    public Party parent() {
        return this.parent;
    }

    public Party child() {
        return this.child;
    }

    public AccountabilityType type() {
        return this.type;
    }
}
