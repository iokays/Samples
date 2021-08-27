package analysispatterns.quantity;

import analysispatterns.name.NamedObject;

public class Currency extends NamedObject {

    public Currency(String name) {
        super(name);
    }

    public static Currency USD = new Currency("USD");
}
