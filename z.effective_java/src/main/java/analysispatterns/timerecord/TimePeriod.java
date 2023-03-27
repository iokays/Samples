package analysispatterns.timerecord;

public class TimePeriod extends TimeRecord {

    private MfDate start;

    private MfDate end;

    public TimePeriod(MfDate start, MfDate end) {
        this.start = start;
        this.end = end;
    }
}
