package analysispatterns.range;

import analysispatterns.timerecord.MfDate;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;

public class DateRange implements Serializable {

    public static DateRange EMPTY = new DateRange(LocalDate.of(2000, 4, 1), LocalDate.of(2000, 1, 1));

    private MfDate start;

    private MfDate end;

    public DateRange(MfDate start, MfDate end) {
        this.start = start;
        this.end = end;
    }

    public DateRange(LocalDate start, LocalDate end) {
        this(new MfDate(start), new MfDate(end));
    }

    public MfDate start() {
        return start;
    }

    public MfDate end() {
        return end;
    }

    public boolean isEmpty() {
        return start.after(end);
    }

    public static DateRange upTo(MfDate end) {
        return new DateRange(new MfDate(LocalDate.of(2000, 1, 1)), end);
    }

    public static DateRange startingOn(MfDate start) {
        return new DateRange(start, new MfDate(LocalDate.of(2099, 12, 31)));
    }


    public boolean includes (MfDate arg) {
        return !arg.before(start) && !arg.after(end);
    }

    public DateRange gap(DateRange arg){
        if (this.overlaps(arg)) return DateRange.EMPTY;
        DateRange lower, higher;
        if (this.compareTo(arg) < 0) {
            lower = this;
            higher = arg;
        }
        else {
            lower = arg;
            higher = this;
        }
        return new DateRange(lower.end.addDays(1), higher.start.addDays(-1));
    }
    public int compareTo(Object arg) {
        DateRange other = (DateRange) arg;
        if (!start.equals(other.start)) return start.compareTo(other.start);
        return end.compareTo(other.end);
    }

    public boolean abuts(DateRange arg) {
        return !this.overlaps(arg) && this.gap(arg).isEmpty();
    }

    public boolean partitionedBy(DateRange[] args) {
        if (!isContiguous(args)) return false;
        return this.equals(DateRange.combination(args));
    }

    public static DateRange combination(DateRange[] args) {
        Arrays.sort(args);
        if (!isContiguous(args)) throw new IllegalArgumentException("Unable to combine date ranges");
        return new DateRange(args[0].start, args[args.length -1].end);
    }

    public static boolean isContiguous(DateRange[] args) {
        Arrays.sort(args);
        for (int i=0; i<args.length - 1; i++) {
            if (!args[i].abuts(args[i+1])) return false;
        }
        return true;
    }


    public boolean overlaps(DateRange arg) {
        return arg.includes(start) || arg.includes(end) || this.includes(arg);
    }

    public boolean includes(DateRange arg) {
        return this.includes(arg.start) && this.includes(arg.end);
    }

    @Override
    public String toString() {
        if (isEmpty()) return "Empty Date Range";
        return start.toString() + " - " + end.toString();
    }

    @Override
    public boolean equals (Object arg) {
        if (! (arg instanceof DateRange)) return false;
        DateRange other = (DateRange) arg;
        return start.equals(other.start) && end.equals(other.end);
    }
    @Override
    public int hashCode() {
        return start.hashCode();
    }

}
