package apsupp.orgainzationstructures.accountability;

import com.google.common.base.Preconditions;

import java.io.Serializable;

public class Accountability implements Serializable {

    private final Party parent;

    private final Party child;

    private final AccountabilityType type;

    public Accountability(Party parent, Party child, AccountabilityType type) {
        this.parent = parent;
        this.parent.friendAddChildAccontabilities(this);
        this.child = child;
        this.child.friendAddParentAccontabilities(this);
        this.type = type;
    }

    public static Accountability create(Party parent, Party child, AccountabilityType type) {
        Preconditions.checkState(canCreate(parent, child, type));
        return new Accountability(parent, child, type);
    }

    public static boolean canCreate(Party parent, Party child, AccountabilityType type) {
        if (parent.equals(child)) {
            return false;
        }

        if (parent.ancestorsInclude(child, type)) {
            return false;
        }
        return true;
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
