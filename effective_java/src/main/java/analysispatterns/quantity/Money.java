package analysispatterns.quantity;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Money implements Comparable {

    private BigInteger amount;
    private Currency currency;

    public double amount() {
        return this.amount.doubleValue() / 100;
    }

    public Currency currency() {
        return this.currency;
    }

    public Money (double amount, Currency currency) {
        this.amount = BigInteger.valueOf(Math.round(amount * 100));
        this.currency = currency;
    }

    private Money(BigInteger amountInPennies, Currency currency, boolean privacyMarker) {
        Preconditions.checkNotNull(amountInPennies);
        Preconditions.checkNotNull(currency);

        this.amount = amountInPennies;
        this.currency = currency;
    }

    public Money(long amount, Currency currency) {
        this.amount = BigInteger.valueOf(amount * 100);
        this.currency = currency;
    }

    public static Money dollars(double amount) {
        return new Money(amount, Currency.USD);
    }

    public Money add(Money arg) {
        this.assertSameCurrencyAs(arg);
        return new Money(amount.add(arg.amount), currency, true);
    }

    public Money subtract(Money arg) {
        return this.add(arg.negate());
    }

    public Money multiply(double arg) {
        return  new Money(amount() * arg, currency);
    }

    public Money[] divide(int denominator) {
        final var bigDenominator = BigInteger.valueOf(denominator);

        Money[] result = new Money[denominator];
        BigInteger simpleResult = amount.divide(bigDenominator);
        for (int i = 0; i < denominator; i++) {
            result[i] = new Money(simpleResult, currency, true);
        }

        var remainder = amount.subtract(simpleResult.multiply(bigDenominator)).intValue();

        for (int i = 0; i < remainder; i++) {
            result[i] = result[i].add(new Money(BigInteger.valueOf(1), currency, true));
        }

        return result;
    }


    public boolean greaterThan(Money arg) {
        return (this.compareTo(arg) == 1);
    }

    public boolean lessThan(Money arg) {
        return (this.compareTo(arg) == -1);
    }

    void assertSameCurrencyAs(Money arg) {
        Preconditions.checkState(Objects.equal(this.currency, arg.currency));
    }

    public Money negate() {
        return new Money(amount.negate(), currency, true);
    }

    @Override
    public int compareTo(Object arg) {
        Money moneyArg = (Money) arg;
        assertSameCurrencyAs(moneyArg);
        return amount.compareTo(moneyArg.amount);
    }

    @Override
    public boolean equals(Object arg) {
        if (this == arg) return true;
        if (arg == null || getClass() != arg.getClass()) return false;
        final var other = (Money) arg;
        return Objects.equal(currency, other.currency) && Objects.equal(amount, other.amount);
    }

    @Override
    public int hashCode() {
        return amount.hashCode();
    }

}
