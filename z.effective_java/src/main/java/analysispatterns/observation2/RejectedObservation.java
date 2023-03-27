package analysispatterns.observation2;

import analysispatterns.timerecord.TimeRecord;

public class RejectedObservation extends Observation {

    private Observation observation;

    public RejectedObservation(ObjectOfCare objectOfCare, TimeRecord applicability, TimeRecord recordingTime, Observation observation) {
        super(objectOfCare, applicability, recordingTime);
        this.observation = observation;
    }

}
