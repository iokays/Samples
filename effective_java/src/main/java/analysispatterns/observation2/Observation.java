package analysispatterns.observation2;

import analysispatterns.timerecord.TimeRecord;

public class Observation {

    private Protocol protocol;

    private TimeRecord applicability;

    private TimeRecord recordingTime;

    private ObjectOfCare objectOfCare;

    public Observation(ObjectOfCare objectOfCare, TimeRecord applicability, TimeRecord recordingTime) {
        this(objectOfCare, null, applicability, recordingTime);
    }

    private Observation(ObjectOfCare objectOfCare, Protocol protocol, TimeRecord applicability, TimeRecord recordingTime) {
        this.objectOfCare = objectOfCare;
        this.protocol = protocol;
        this.applicability = applicability;
        this.recordingTime = recordingTime;
    }
}
