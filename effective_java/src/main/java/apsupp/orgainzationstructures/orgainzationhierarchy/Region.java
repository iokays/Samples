package apsupp.orgainzationstructures.orgainzationhierarchy;

import com.google.common.base.Preconditions;

import java.util.Objects;

public class Region extends Organization {

    public Region(String name, Organization parent) {
        super(name, parent);
    }

    @Override
    void assertValidParent(Organization parent) {
        Objects.nonNull(parent);
        Preconditions.checkState(parent instanceof OperatingUnit);
        super.assertValidParent(parent);
    }
}
