package analysispatterns.party;

import analysispatterns.name.Address;
import analysispatterns.name.EmailAddress;
import analysispatterns.name.TelephoneNumber;

public class Organization extends Party {

    public Organization(String name, TelephoneNumber telephoneNumber, Address address, EmailAddress emailAddress) {
        super(name, telephoneNumber, address, emailAddress);
    }
}
