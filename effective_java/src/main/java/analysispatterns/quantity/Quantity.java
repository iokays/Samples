package analysispatterns.quantity;

import java.io.Serializable;
import java.util.StringJoiner;

public class Quantity implements Serializable {

    private Number amount;

    private Unit units;

    protected Unit units() {
        return units;
    }

    public Number amount() {
        return amount;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Quantity.class.getSimpleName() + "[", "]")
                .add("amount=" + amount)
                .add("units=" + units)
                .toString();
    }
}
