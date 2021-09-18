package analysispatterns.effectivity;

import analysispatterns.timerecord.MfDate;
import analysispatterns.range.DateRange;

import java.io.Serializable;

public class Employment implements Serializable {

    private Company company;
    private DateRange effective;

    public Employment(Company company, DateRange effective) {
        this.company = company;
        this.effective = effective;
    }

    public Employment(Company company, final MfDate startDate) {
        this(company, DateRange.startingOn(startDate));
    }

    public void effective(DateRange effective) {
        this.effective = effective;
    }

    public boolean isEffectiveOn(MfDate arg) {
        return effective.includes(arg);
    }

    public void end(final MfDate endDate) {
        this.effective = new DateRange(this.effective.start(), endDate);
    }

    public Company company() {
        return company;
    }
}
