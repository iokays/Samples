package analysispatterns.orgainzationstructures.orgainzationhierarchy;

import com.google.common.base.Preconditions;

import java.util.Objects;

public class SalesOffice extends Organization {

    public SalesOffice(String name, Organization parent) {
        super(name, parent);
    }

    @Override
    void assertValidParent(Organization parent) {
        Objects.nonNull(parent);
        Preconditions.checkState(parent instanceof Division);
        super.assertValidParent(parent);
    }
}
