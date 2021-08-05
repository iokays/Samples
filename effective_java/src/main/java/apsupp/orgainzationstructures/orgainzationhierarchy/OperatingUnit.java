package apsupp.orgainzationstructures.orgainzationhierarchy;

import java.util.Objects;

public class OperatingUnit extends Organization {

    public OperatingUnit(String name, Organization parent) {
        super(name, parent);
    }

    @Override
    void assertValidParent(Organization parent) {
        Objects.isNull(parent);
        super.assertValidParent(parent);
    }
}
