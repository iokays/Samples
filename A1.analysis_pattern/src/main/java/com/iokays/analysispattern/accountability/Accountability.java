package com.iokays.analysispattern.accountability;

public class Accountability {

    private final Party parent;
    private final Party child;

    private final AccountabilityType type;

    private Accountability(Party parent, Party child, AccountabilityType type) {
        this.parent = parent;
        parent.friendAddChildAccountability(this);
        this.child = child;
        child.friendAddParentAccountability(this);
        this.type = type;
    }

    public static Accountability create(Party parent, Party child, AccountabilityType type) {
        if (!canCreate(parent, child, type)) {
            throw new IllegalArgumentException("Invalid Accountability");
        }
        return new Accountability(parent, child, type);
    }

    static boolean canCreate(Party parent, Party child, AccountabilityType type) {
        return !parent.equals(child) && !parent.ancestorsInclude(child, type);
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
