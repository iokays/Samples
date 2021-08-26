package analysispatterns.quantity;

import java.math.BigDecimal;

public class Money extends Quantity {

    public Money(MoneyNumber amount, final Currency currency) {
        super(amount, currency);
    }

    public Money(BigDecimal amount, final Currency currency) {
        super(new MoneyNumber(amount), currency);
    }

    public Money(Integer amount, final Currency currency) {
        super(new MoneyNumber(BigDecimal.valueOf(amount)), currency);
    }

    public static Money dollars(Integer value) {
        return new Money(value, new Currency("dollar"));
    }

    public Currency currency() {
        return (Currency) this.units();
    }

    @Override
    public Money add(Quantity quantity) {
        return (Money) super.add(quantity);
    }

    public static void main(String[] args) {
        System.out.println(Money.dollars(11).add(Money.dollars(12)));
    }
}
