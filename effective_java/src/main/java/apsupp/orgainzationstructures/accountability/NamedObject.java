package apsupp.orgainzationstructures.accountability;

import java.io.Serializable;

public class NamedObject implements Serializable {

    private final String name;

    public NamedObject(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }
}
