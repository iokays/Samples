package apsupp.orgainzationstructures.accountability;

import java.io.Serializable;

public class Accountability implements Serializable {

    private final Party parent;

    private final Party child;

    private final AccountabilityType type;

    private final AccountabilityTimePeriod timePeriod;

    public Accountability(Party parent, Party child, AccountabilityType type, AccountabilityTimePeriod timePeriod) {
        this.parent = parent;
        this.parent.friendAddChildAccontabilities(this);
        this.child = child;
        this.child.friendAddParentAccontabilities(this);
        this.type = type;
        this.timePeriod = timePeriod;
    }

    public static Accountability create(Party parent, Party child, AccountabilityType type, AccountabilityTimePeriod timePeriod) {
        return new Accountability(parent, child, type, timePeriod);
    }

    public Party parent() {
        return parent;
    }

    public Party child() {
        return child;
    }

    public AccountabilityType type() {
        return type;
    }

}
