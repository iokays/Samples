package analysispatterns.observation2;

import analysispatterns.timerecord.TimeRecord;

public class Absence extends CategoryObservation {

    public Absence(ObjectOfCare objectOfCare, ObservationConcept observationConcept, TimeRecord applicability, TimeRecord recordingTime) {
        super(objectOfCare, observationConcept, applicability, recordingTime);
    }

}
