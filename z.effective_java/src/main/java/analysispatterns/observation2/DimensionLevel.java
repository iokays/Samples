package analysispatterns.observation2;

import analysispatterns.name.NamedObject;

public class DimensionLevel extends NamedObject {

    private DimensionLevel(String name) {
        super(name);
    }

    public static DimensionLevel of(final String name) {
        return new DimensionLevel(name);
    }



}
