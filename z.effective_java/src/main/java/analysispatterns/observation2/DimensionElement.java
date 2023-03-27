package analysispatterns.observation2;

import analysispatterns.name.NamedObject;

public class DimensionElement extends NamedObject {

    public static final String ALL = "all";

    private Dimension dimension;

    private DimensionElement parent;

    public DimensionElement(final String name, Dimension dimension) {
        this(name, dimension, null);
    }

    public DimensionElement(String name, Dimension dimension, DimensionElement parent) {
        super(name);
        this.dimension = dimension;
        this.parent = parent;
    }

    public DimensionElement parent() {
        return parent;
    }

    public static DimensionElement all(final Dimension dimension) {
        return new DimensionElement(ALL, dimension);
    }

    public void parent(DimensionElement parent) {
        if (null != parent && !parent.getClass().equals(this.getClass())) {
            throw new RuntimeException("parent must be of same subtype");
        }
        this.parent = parent;
    }

    public int level() {
        if (this.parent.name().equals(DimensionElement.ALL)) {
            return 1;
        } else {
            return 1 + this.parent.level();
        }
    }
}
