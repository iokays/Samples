package analysispatterns.orgainzationstructures;

import analysispatterns.TimePeriod;

import java.io.Serializable;

public class OrganizationStructure implements Serializable {

    private Organization parent;

    private Organization child;

    private TimePeriod timePeriod;

    private OrganizationStructureType organizationStructureType;

    public OrganizationStructure(Organization parent, Organization child, TimePeriod timePeriod, OrganizationStructureType organizationStructureType) {
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

    public TimePeriod timePeriod() {
        return timePeriod;
    }

    public OrganizationStructureType organizationStructureType() {
        return organizationStructureType;
    }
}
