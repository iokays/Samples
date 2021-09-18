package analysispatterns.observation2;

import analysispatterns.timerecord.TimeRecord;

public class Presence extends CategoryObservation {

    public Presence(ObjectOfCare objectOfCare, ObservationConcept observationConcept, TimeRecord applicability, TimeRecord recordingTime) {
        super(objectOfCare, observationConcept, applicability, recordingTime);
    }

}
