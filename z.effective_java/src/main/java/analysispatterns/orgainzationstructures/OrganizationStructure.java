package analysispatterns.orgainzationstructures;

import analysispatterns.TimePeriod;
import analysispatterns.range.DateRange;
import com.google.common.base.Preconditions;

import java.io.Serializable;

public class OrganizationStructure implements Serializable {

    private Organization parent;

    private Organization child;

    private DateRange timePeriod;

    private OrganizationStructureType organizationStructureType;

    public OrganizationStructure(Organization parent, Organization child, DateRange timePeriod, OrganizationStructureType organizationStructureType) {
        this.organizationStructureType.isValid(parent, child);

        this.parent = parent;
        this.parent.addChildrenOrganizationStructures(this);
        this.child = child;
        this.child.addParentOrganizationStructures(this);
        this.timePeriod = timePeriod;
        this.organizationStructureType = organizationStructureType;
    }

    public Organization parent() {
        return parent;
    }

    public Organization child() {
        return child;
    }

    public DateRange timePeriod() {
        return timePeriod;
    }

    public OrganizationStructureType organizationStructureType() {
        return organizationStructureType;
    }
}
