package analysispatterns.range;

import analysispatterns.dualtimerecord.LocalDateTimePoint;
import org.springframework.util.comparator.Comparators;


public class LocalDateTimeMagnitude extends Magnitude {

    private LocalDateTimePoint localDateTime;

    @Override
    public int compareTo(Object o) {
        return Comparators.comparable().compare(this, o);
    }
}
