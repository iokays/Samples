package analysispatterns.dualtimerecord;

import java.time.LocalDateTime;

public class LocalDateTimePoint extends TimePoint {

    private LocalDateTime time;

    public LocalDateTimePoint(LocalDateTime time) {
        this.time = time;
    }

    public static LocalDateTimePoint now() {
        return new LocalDateTimePoint(LocalDateTime.now());
    }
}
