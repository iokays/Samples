package analysispatterns.dualtimerecord;

import java.time.LocalDate;

public class MfDate extends TimeRecord {

    private static final MfDate test = null;

    private LocalDate date;

    public MfDate(LocalDate date) {
        super();
        this.date = date;
    }

    public LocalDate date() {
        return this.date;
    }

    public static final MfDate now() {
        return new MfDate(LocalDate.now());
    }

    public boolean after (MfDate arg) {
        return date().isAfter(arg.date());
    }
    public boolean before (MfDate arg) {
        return date().isBefore(arg.date());
    }
    public int compareTo(Object arg) {
        MfDate other = (MfDate) arg;
        return date().compareTo(other.date());
    }

    public boolean equals(Object arg) {
        if (this == arg) return true;
        if (! (arg instanceof MfDate)) return false;
        MfDate other = (MfDate) arg;
        return (date.equals(other.date));
    }

    public MfDate addDays(int arg) {
        return new MfDate(date().plusDays(arg));
    }

    public MfDate minusDays(int arg) {
        return addDays(-arg);
    }

}
