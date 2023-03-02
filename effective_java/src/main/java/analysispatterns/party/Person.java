package analysispatterns.party;

import analysispatterns.name.Address;
import analysispatterns.name.EmailAddress;
import analysispatterns.name.TelephoneNumber;

public class Person extends Party {

    public Person(String name, TelephoneNumber telephoneNumber, Address address, EmailAddress emailAddress) {
        super(name, telephoneNumber, address, emailAddress);
    }
}
