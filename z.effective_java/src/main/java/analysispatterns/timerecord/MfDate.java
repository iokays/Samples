package analysispatterns.timerecord;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MfDate extends TimeRecord implements Comparable {

    private static MfDate myToday;

    private LocalDate date;

    public MfDate(int year, int month, int dayOfMonth) {
        this(LocalDate.of(year, month, dayOfMonth));
    }

    public MfDate() {
        this(LocalDate.now());
    }

    public MfDate(LocalDate date) {
        super();
        this.date = date;
    }

    public LocalDate date() {
        return this.date;
    }

    public boolean after (MfDate arg) {
        return date().isAfter(arg.date());
    }
    public boolean before (MfDate arg) {
        return date().isBefore(arg.date());
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

    public static void setToday(int year, int month, int day) {
        MfDate.setToday(new MfDate(year, month, day));
    }
    public static void setToday(MfDate arg) {
        myToday = arg;
    }

    public static MfDate today() {
        return ((myToday == null) ? new MfDate() : myToday);
    }

    @Override
    public int compareTo(Object arg) {
        MfDate other = (MfDate) arg;
        return date().compareTo(other.date());
    }

    @Override
    public String toString() {
        if (null == this.date) {
            return null;
        }
        return this.date.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
