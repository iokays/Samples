package com.iokays.analysispattern.base;

import java.math.BigInteger;

public class Money implements Comparable {
    private BigInteger myAmount;
    private Currency myCurrency;

    public Money(double amount, Currency currency) {
        this.myAmount = BigInteger.valueOf(Math.round(amount * 100));
        this.myCurrency = currency;
    }

    public Money(long amount, Currency currency) {
        this.myAmount = BigInteger.valueOf(amount * 100);
        this.myCurrency = currency;
    }

    private Money(BigInteger amountInPennies, Currency currency) {
        assert amountInPennies != null;
        assert currency != null;
        this.myAmount = amountInPennies;
        this.myCurrency = currency;
    }

    public Money add(Money arg) {
        checkSameCurrencies(arg);
        return new Money(myAmount.add(arg.myAmount), myCurrency);
    }

    public Money subtract(Money arg) {
        return this.add(arg.negate());
    }

    /*package*/ void checkSameCurrencies(Money arg) {
        if (!(myCurrency.equals(arg.myCurrency))) {
            throw new IllegalArgumentException("currency mismatch");
        }
    }

    public Money negate() {
        return new Money(myAmount.negate(), myCurrency);
    }

    /*package*/ double amount() {
        return myAmount.doubleValue() / 100;
    }

    public Currency currency() {
        return myCurrency;
    }

    @Override
    public int compareTo(Object arg) {
        throw new RuntimeException("??");
    }

    public int compareTo(Money arg) {
        checkSameCurrencies(arg);
        return myAmount.compareTo(arg.myAmount);
    }

    @Override
    public boolean equals(Object arg) {
        if (!(arg instanceof Money)) {
            return false;
        }
        Money other = (Money) arg;
        return (myCurrency.equals(other.myCurrency) && (myAmount.equals(other.myAmount)));
    }

    public String formatString() {
        return myCurrency.formatString(amount());
    }

    public boolean greaterThan(Money arg) {
        return (this.compareTo(arg) == 1);
    }

    public boolean lessThan(Money arg) {
        return (this.compareTo(arg) == -1);
    }

    @Override
    public int hashCode() {
        return myAmount.hashCode();
    }

    public boolean isNegative() {
        return (myAmount.compareTo(BigInteger.ZERO) == -1);
    }

    public boolean isPositive() {
        return (myAmount.compareTo(BigInteger.ZERO) == 1);
    }

    public boolean isZero() {
        return myAmount.signum() == 0;
    }

    public String localString() {
        return myCurrency.getFormat().format(amount());
    }

    public Money multiply(double arg) {
        return new Money(amount() * arg, myCurrency);
    }

    @Override
    public String toString() {
        return myCurrency.toString() + " " + amount();
    }

    public Money[] divide(int denominator) {
        BigInteger bigDenominator = BigInteger.valueOf(denominator);
        Money[] result = new Money[denominator];
        BigInteger simpleResult = myAmount.divide(bigDenominator);
        for (int i = 0; i < denominator; i++) {
            result[i] = new Money(simpleResult, myCurrency);
        }
        int remainder = myAmount.subtract(simpleResult.multiply(bigDenominator)).intValue();
        for (int i = 0; i < remainder; i++) {
            result[i] = result[i].add(new Money(BigInteger.valueOf(1), myCurrency));
        }
        return result;
    }

    public static Money dollars(double amount) {
        return new Money(amount, Currency.USD);
    }
}
