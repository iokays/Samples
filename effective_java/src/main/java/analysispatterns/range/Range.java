package analysispatterns.range;

import java.io.Serializable;

public class Range implements Serializable {

    private Magnitude upper;

    private Magnitude lower;

    private boolean isUpperInclusive;

    private boolean isLowerInclusive;

    public boolean includes(final Magnitude magnitude) {
        //TODO
        return false;
    }

    public boolean overlaps(final Range range) {
        //TODO
        return false;
    }

    public boolean abuts(final Range range) {
        //TODO
        return false;
    }

}
