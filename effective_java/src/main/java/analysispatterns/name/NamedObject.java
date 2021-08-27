package analysispatterns.name;

import com.google.common.base.Objects;

import java.io.Serializable;
import java.util.StringJoiner;

public class NamedObject implements Serializable {

    private String name = "no name";

    public NamedObject(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NamedObject that = (NamedObject) o;
        return Objects.equal(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
