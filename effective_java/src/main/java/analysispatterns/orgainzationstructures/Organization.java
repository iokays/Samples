package analysispatterns.orgainzationstructures;

import analysispatterns.NamedObject;
import analysispatterns.TimePeriod;
import analysispatterns.accountability.AccountabilityType;
import analysispatterns.accountability.Party;
import com.google.common.collect.Sets;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Organization extends NamedObject {

    private final Set<OrganizationStructure> parentOrganizationStructures = Sets.newHashSet();

    private final Set<OrganizationStructure> childrenOrganizationStructures = Sets.newHashSet();

    public Organization(String name) {
        super(name);
    }

    protected void addChildrenOrganizationStructures(final OrganizationStructure arg) {
        Objects.requireNonNull(arg);
        this.childrenOrganizationStructures.add(arg);
    }

    protected void addParentOrganizationStructures(final OrganizationStructure arg) {
        Objects.requireNonNull(arg);
        this.parentOrganizationStructures.add(arg);
    }

    public Set<Organization> parents() {
        return this.parentOrganizationStructures.stream().map(OrganizationStructure::parent).collect(Collectors.toSet());
    }

    public boolean ancestorsInclude(Organization sample, OrganizationStructureType type) {
        return this.parents(type).stream().filter(v -> v.equals(sample) || v.ancestorsInclude(sample, type)).findFirst().isPresent();
    }

    public Set<Organization> parents(final OrganizationStructureType aType) {
        return this.parentOrganizationStructures.stream()
                .filter(v -> v.organizationStructureType().equals(aType))
                .map(OrganizationStructure::parent).collect(Collectors.toSet());
    }

    public Set<Organization> parents(final OrganizationStructureType aType, final LocalDate aDate) {
        return this.parentOrganizationStructures.stream()
                .filter(v -> v.organizationStructureType().equals(aType) && v.timePeriod().contains(aDate))
                .map(OrganizationStructure::parent).collect(Collectors.toSet());
    }

    public Set<Organization> children() {
        return this.childrenOrganizationStructures.stream().map(OrganizationStructure::child).collect(Collectors.toSet());
    }

    public Set<Organization> children(final OrganizationStructureType aType) {
        return this.childrenOrganizationStructures.stream()
                .filter(v -> v.organizationStructureType().equals(aType))
                .map(OrganizationStructure::child).collect(Collectors.toSet());
    }

    public Set<Organization> children(final OrganizationStructureType aType, final LocalDate aDate) {
        return this.childrenOrganizationStructures.stream()
                .filter(v -> v.organizationStructureType().equals(aType) && v.timePeriod().contains(aDate))
                .map(OrganizationStructure::child).collect(Collectors.toSet());
    }

}
