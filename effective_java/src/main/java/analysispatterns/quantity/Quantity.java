package analysispatterns.quantity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Quantity implements Serializable {

    private BigDecimal amount;

    private Unit units;

    public Quantity(BigDecimal amount, Unit unit) {
        this.amount = amount;
        this.units = unit;
    }

    public Quantity(Integer amount, String unit) {
        this(BigDecimal.valueOf(amount), new Unit(unit));
    }

}
