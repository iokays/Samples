package analysispatterns.quantity;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

import java.math.BigInteger;

public class Length implements Quantity, Comparable {

    private BigInteger amount;
    private Linear linear;

    public Length(double amount, Linear linear) {
        this.amount = BigInteger.valueOf(Math.round(amount * 100));
        this.linear = linear;
    }

    public Length(long amount, Linear linear) {
        this.amount = BigInteger.valueOf(amount * 100);
        this.linear = linear;
    }

    public static Length metric(double amount) {
        return new Length(amount, Linear.METRIC);
    }

    void assertSameDistanceAs(Length arg) {
        Preconditions.checkState(Objects.equal(this.linear, arg.linear));
    }

    @Override
    public int compareTo(Object arg) {
        Length lengthArg = (Length) arg;
        assertSameDistanceAs(lengthArg);
        return amount.compareTo(lengthArg.amount);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Length{");
        sb.append("amount=").append(amount);
        sb.append(", distance=").append(linear);
        sb.append('}');
        return sb.toString();
    }
}
