package analysispatterns.observation2;

import analysispatterns.timerecord.TimeRecord;

import java.util.List;

public class AssociatedObservation extends Observation {

    private List<Observation> evidence;

    private AssociativeFunction function;

    public AssociatedObservation(ObjectOfCare objectOfCare, TimeRecord applicability, TimeRecord recordingTime) {
        super(objectOfCare, applicability, recordingTime);
    }

}
