package analysispatterns.accountablilityknowledgelevel;

import java.io.Serializable;

public class ConnectionRule implements Serializable {

    private PartyType allowedParent;

    private PartyType allowedChild;

    public ConnectionRule(PartyType allowedParent, PartyType allowedChild) {
        this.allowedParent = allowedParent;
        this.allowedChild = allowedChild;
    }

    public boolean isValid(final Party parent, final Party child) {
        return (parent.type().equals(allowedParent) && child.type().equals(allowedChild));
    }

}
