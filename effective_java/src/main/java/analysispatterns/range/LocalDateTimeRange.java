package analysispatterns.range;

public class LocalDateTimeRange extends Range {

    public LocalDateTimeRange(LocalDateTimeMagnitude upper, LocalDateTimeMagnitude lower) {
        super(upper, lower, true, true);
    }
}
