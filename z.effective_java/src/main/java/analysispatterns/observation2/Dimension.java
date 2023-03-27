package analysispatterns.observation2;

import analysispatterns.name.NamedObject;

public class Dimension extends NamedObject {

    private DimensionLevel[] dimensionLevels;

    public Dimension(String name) {
        super(name);
    }

    public Dimension(String name, String... levels) {
        this(name);
        if (null != levels) {
            this.dimensionLevels = new DimensionLevel[levels.length];
            for (int i = 0; i < levels.length; i++) {
                this.dimensionLevels[i] = DimensionLevel.of(levels[i]);
            }
        }
    }

    public DimensionLevel level(final Integer level) {
        return dimensionLevels[level - 1];
    }

}
