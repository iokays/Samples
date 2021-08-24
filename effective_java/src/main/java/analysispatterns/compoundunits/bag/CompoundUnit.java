package analysispatterns.compoundunits.bag;

import analysispatterns.compoundunits.Unit;
import org.apache.commons.collections.Bag;

public class CompoundUnit extends Unit {

    private Bag direct;

    private Bag inverse;

    public CompoundUnit(String name) {
        super(name);
    }
}
