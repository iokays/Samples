package analysispatterns.enterprisesegment.figure43;

import analysispatterns.NamedObject;
import com.google.common.base.Preconditions;

public class DimensionElement extends NamedObject {

    private DimensionElement parent;

    public DimensionElement(String name) {
        super(name);
    }

    public void setParent(DimensionElement parent) {
        if (null != parent) {
            Preconditions.checkState(parent.getClass().equals(this.getClass()));
        }
        this.parent = parent;
    }

}
