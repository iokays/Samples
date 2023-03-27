package analysispatterns.organizationhierarchy;

import com.google.common.base.Preconditions;

import java.util.Objects;

public class Division extends Organization {

    public Division(String name, Organization parent) {
        super(name, parent);
    }

    @Override
    void assertValidParent(Organization parent) {
        Objects.nonNull(parent);
        Preconditions.checkState(parent instanceof Region);
        super.assertValidParent(parent);
    }
}
