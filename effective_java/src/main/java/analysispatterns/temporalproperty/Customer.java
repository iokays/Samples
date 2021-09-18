package analysispatterns.temporalproperty;

import analysispatterns.timerecord.MfDate;
import analysispatterns.name.NamedObject;

public class Customer extends NamedObject {

    private BitemporalCollection addresses = new BitemporalCollection();

    public Customer(String name) {
        super(name);
    }

    public Address getAddress(final MfDate mfDate) {
        return (Address) addresses.get(mfDate);
    }

    public Address getAddress(MfDate actualDate, MfDate recordDate) {
        return (Address) addresses.get(actualDate, recordDate);
    }

    public Address getAddress() {
        return (Address) addresses.get(MfDate.today());
    }

    public void putAddress(MfDate mfDate, Address value) {
        addresses.put(mfDate, value);
    }


}
