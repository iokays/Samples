package apsupp.orgainzationstructures.accountability;

import java.io.Serializable;
import java.time.LocalDate;

public class AccountabilityTimePeriod implements Serializable {

    private final LocalDate start;

    private final LocalDate end;

    public AccountabilityTimePeriod(LocalDate start, LocalDate end) {
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
