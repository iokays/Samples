package analysispatterns.quantity;

import com.google.common.base.Preconditions;

import java.io.Serializable;
import java.util.StringJoiner;

public class Quantity implements Serializable {

    private Number amount;

    private Unit units;

    public Quantity add(Quantity quantity) {
        Preconditions.checkNotNull(quantity);
        Preconditions.checkState(this.units.equals(quantity.units()));
        return new Quantity(this.amount.add(quantity.amount), this.units);
    }

    public Quantity(Number amount, Unit unit) {
        this.amount = amount;
        this.units = unit;
    }

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
