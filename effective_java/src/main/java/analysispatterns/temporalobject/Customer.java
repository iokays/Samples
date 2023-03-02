package analysispatterns.temporalobject;

import analysispatterns.name.NamedObject;
import analysispatterns.name.Phone;
import analysispatterns.quantity.Money;
import analysispatterns.temporalproperty.Address;
import analysispatterns.temporalproperty.SingleTemporalCollection;
import analysispatterns.temporalproperty.TemporalCollection;

public class Customer extends NamedObject {

    private TemporalCollection history = new SingleTemporalCollection();

    public Customer(String name) {
        super(name);
    }

    public String name() {
        return current().name();
    }

    public Address address() {
        return current().address();
    }

    public Money creditLimit() {
        return current().creditLimit();
    }

    public Phone phone() {
        return current().phone();
    }

    public void setAddress(Address arg) {
        CustomerVersion workingCopy = getWorkingCopy();
        workingCopy.setAddress(arg);
        history.put(workingCopy);
    }

    public void setPhone(Phone arg) {
        CustomerVersion workingCopy = getWorkingCopy();
        workingCopy.setPhone(arg);
        history.put(workingCopy);
    }

    public void setCreditLimit(Money arg) {
        CustomerVersion workingCopy = getWorkingCopy();
        workingCopy.setCreditLimit(arg);
        history.put(workingCopy);
    }


    private CustomerVersion current() {
        return (CustomerVersion)history.get();
    }

    public CustomerVersion getWorkingCopy() {
        return current().copy();
    }

}
