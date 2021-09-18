package analysispatterns.observation2;

import analysispatterns.timerecord.TimeRecord;

public class CategoryObservation extends Observation {

    private ObservationConcept observationConcept;

    public CategoryObservation(ObjectOfCare objectOfCare, ObservationConcept observationConcept, TimeRecord applicability, TimeRecord recordingTime) {
        super(objectOfCare, applicability, recordingTime);
        this.observationConcept = observationConcept;
    }

}
