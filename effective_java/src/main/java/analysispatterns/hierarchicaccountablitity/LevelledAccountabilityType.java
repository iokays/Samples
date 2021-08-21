package analysispatterns.hierarchicaccountablitity;

import analysispatterns.partytypegeneralizations.PartyType;

public class LevelledAccountabilityType extends AccountabilityType {

    private PartyType[] levels;

    public LevelledAccountabilityType(String name) {
        super(name);
    }

    public void setLevels(PartyType[] levels) {
        this.levels = levels;
    }

    @Override
    protected boolean areValidPartyTypes(Party parent, Party child) {
        for (int i = 0; i < levels.length; i++) {
            if (parent.type().equals(levels[i])) {
                return child.type().equals(levels[i+1]);
            }
        }
        return false;
    }
}
