package analysispatterns.observation2;

import analysispatterns.quantity.Quantity;
import analysispatterns.timerecord.TimeRecord;

public class Measurement extends Observation {

    private Quantity quantity;

    private PhenomenonType phenomenonType;

    public Measurement(ObjectOfCare objectOfCare, TimeRecord applicability, TimeRecord recordingTime, Quantity quantity, PhenomenonType phenomenonType) {
        super(objectOfCare, applicability, recordingTime);
        this.quantity = quantity;
        this.phenomenonType = phenomenonType;
    }

}
