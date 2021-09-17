package analysispatterns.measurement;

import analysispatterns.name.NamedObject;
import com.google.common.collect.Lists;

import java.util.List;

public class Person extends NamedObject {

    private final List<Measurement> measurements;

    public Person(String name) {
        super(name);
        this.measurements = Lists.newArrayList();
    }

    public void addMeasurement(final Measurement measurement) {
        this.measurements.add(measurement);
    }
}
