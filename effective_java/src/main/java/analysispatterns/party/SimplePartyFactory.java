package analysispatterns.party;

public class SimplePartyFactory {

    public static Party create(final String type, final String aName, final String aTelephoneNumber, final String anAddress, final String anEmailAddress) {
        final var telephoneNumber = new TelephoneNumber(aTelephoneNumber);
        final var address = new Address(anAddress);
        final var emailAddress = new EmailAddress(anEmailAddress);
        if ("person".equals(type)) {
            return new Person(aName, telephoneNumber, address, emailAddress);
        }

        if ("organization".equals(type)) {
            return new Organization(aName, telephoneNumber, address, emailAddress);
        }

        throw new RuntimeException("type not found");
    }

}
