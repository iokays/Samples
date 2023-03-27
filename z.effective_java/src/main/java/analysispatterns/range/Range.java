package analysispatterns.range;

import com.google.common.base.Preconditions;

import java.io.Serializable;
import java.util.Objects;

public class Range implements Serializable {

    private Magnitude upper;

    private Magnitude lower;

    private boolean isUpperInclusive;

    private boolean isLowerInclusive;

    public Range(Magnitude upper, Magnitude lower, boolean isUpperInclusive, boolean isLowerInclusive) {
        Preconditions.checkNotNull(upper);
        Preconditions.checkNotNull(lower);
        Preconditions.checkState(upper.compareTo(lower) != -1);
        this.upper = upper;
        this.lower = lower;
        this.isUpperInclusive = isUpperInclusive;
        this.isLowerInclusive = isLowerInclusive;
    }

    public boolean includes(final Magnitude magnitude) {
        Objects.requireNonNull(magnitude);
        if (null == magnitude) {
            return false;
        }

        final var i = this.upper.compareTo(magnitude);
        final var j = this.lower.compareTo(magnitude);

        return (i == -1 || (isUpperInclusive && i == 0)) && (j == 1 || (isLowerInclusive && j == 0));
    }

//    public boolean overlaps(final Range range) {
//        //TODO
//        return false;
//    }
//
//    public boolean abuts(final Range range) {
//        //TODO
//        return false;
//    }

}
