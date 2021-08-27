package analysispatterns.dualtimerecord;

import java.time.LocalDateTime;

public class TimePoint extends TimeRecord {

    private LocalDateTime point;

    public TimePoint(LocalDateTime point) {
        this.point = point;
    }

    public static final TimePoint now() {
        return new TimePoint(LocalDateTime.now());
    }
}
