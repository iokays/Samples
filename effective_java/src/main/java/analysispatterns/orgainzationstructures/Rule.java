package analysispatterns.orgainzationstructures;

import java.io.Serializable;
import java.util.Objects;

public class Rule implements Serializable {

    public Class[] classes;

    public Rule(Class... classes) {
        Objects.requireNonNull(classes);
        this.classes = classes;
    }

    public boolean isValid(final Organization parent, final Organization child) {
        for (int i = 0; i < classes.length - 1; i++) {
            if (parent.getClass().equals(classes[i]) && child.getClass().equals(classes[i+1])) {
                return true;
            }
        }
        return false;
    }

}
