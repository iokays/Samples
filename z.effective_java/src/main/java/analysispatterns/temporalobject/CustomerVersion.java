package analysispatterns.temporalobject;

import analysispatterns.name.NamedObject;
import analysispatterns.name.Phone;
import analysispatterns.quantity.Money;
import analysispatterns.temporalproperty.Address;


public class CustomerVersion extends NamedObject {

    public CustomerVersion(String name) {
        super(name);
    }

    public CustomerVersion(String name, Address address, Phone phone, Money creditLimit) {
        super(name);
        this.address = address;
        this.phone = phone;
        this.creditLimit = creditLimit;
    }

    private Address address;

    private Phone phone;

    private Money creditLimit;

    CustomerVersion copy() {
        return new CustomerVersion(this.name(), address, phone, creditLimit);
    }

    public Address address() {
        return address;
    }

    public Phone phone() {
        return phone;
    }

    public Money creditLimit() {
        return creditLimit;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public void setCreditLimit(Money creditLimit) {
        this.creditLimit = creditLimit;
    }
}
