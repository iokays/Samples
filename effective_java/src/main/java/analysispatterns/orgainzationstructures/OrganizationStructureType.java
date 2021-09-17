package analysispatterns.orgainzationstructures;

import analysispatterns.name.NamedObject;

public class OrganizationStructureType extends NamedObject {

    private Rule rule;

    public boolean isValid(final Organization parent, final Organization child) {
        return null != this.getRule() && this.getRule().isValid(parent, child);
    }

    public OrganizationStructureType(String name) {
        super(name);
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }

    public Rule getRule() {
        return rule;
    }
}
