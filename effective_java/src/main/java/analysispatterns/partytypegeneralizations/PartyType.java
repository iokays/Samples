package analysispatterns.partytypegeneralizations;

import analysispatterns.name.NamedObject;
import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.Set;

public class PartyType extends NamedObject {

    private final Set<PartyType> parents = Sets.newHashSet();

    public PartyType(String name) {
        super(name);
    }

    public PartyType(String name, PartyType... types) {
        this(name);
        if (null != types) {
            Arrays.stream(types).forEach(parents::add);
        }
    }

    public Set<PartyType> parents() {
        return parents;
    }
}
