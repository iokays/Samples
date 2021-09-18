package analysispatterns.observation2;

import java.util.List;

public class AssociativeFunction {

    private List<ObservationConcept> arguments;

    private ObservationConcept product;

    public AssociativeFunction(List<ObservationConcept> arguments) {
        this.arguments = arguments;
    }

    public ObservationConcept product() {
        return product;
    }
}
