package analysispatterns.party;

import analysispatterns.name.Address;
import analysispatterns.name.EmailAddress;
import analysispatterns.name.NamedObject;
import analysispatterns.name.TelephoneNumber;

public class Party extends NamedObject {

    private TelephoneNumber telephoneNumber;

    private Address address;

    private EmailAddress emailAddress;

    public Party(String name, TelephoneNumber telephoneNumber, Address address, EmailAddress emailAddress) {
        super(name);
        this.telephoneNumber = telephoneNumber;
        this.address = address;
        this.emailAddress = emailAddress;
    }

    public TelephoneNumber telephoneNumber() {
        return telephoneNumber;
    }

    public Address address() {
        return address;
    }

    public EmailAddress emailAddress() {
        return emailAddress;
    }
}
