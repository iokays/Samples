package analysispatterns.observation2;

import analysispatterns.timerecord.TimeRecord;

public class ActiveObservation extends Observation {

    public ActiveObservation(ObjectOfCare objectOfCare, TimeRecord applicability, TimeRecord recordingTime) {
        super(objectOfCare, applicability, recordingTime);
    }
}
