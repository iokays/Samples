package analysispatterns;

import java.io.Serializable;
import java.time.LocalDate;

public class TimePeriod implements Serializable {

    private final LocalDate start;

    private final LocalDate end;

    public boolean contains(final LocalDate aDate) {
        return null != aDate && !start.isAfter(aDate) && !end.isBefore(aDate);
    }

    public TimePeriod(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    public LocalDate start() {
        return start;
    }

    public LocalDate end() {
        return end;
    }
}
