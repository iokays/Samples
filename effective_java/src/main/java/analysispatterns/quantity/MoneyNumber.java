package analysispatterns.quantity;

import com.google.common.base.Preconditions;

import java.math.BigDecimal;

public class MoneyNumber extends Number {

    private BigDecimal amount;

    public MoneyNumber(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public int compareTo(Object o) {
        Preconditions.checkNotNull(o);
        Preconditions.checkState(o instanceof MoneyNumber);
        return this.amount.compareTo(((MoneyNumber) o).amount);
    }

    public MoneyNumber add(final Number aMoneyNumber) {
        Preconditions.checkNotNull(aMoneyNumber);
        return new MoneyNumber(this.amount.add(((MoneyNumber) aMoneyNumber).amount));
    }

}
