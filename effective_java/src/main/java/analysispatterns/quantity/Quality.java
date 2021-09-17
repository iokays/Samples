package analysispatterns.quantity;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

import java.math.BigInteger;

public class Quality implements Quantity, Comparable{

    private BigInteger amount;
    private Weight weight;

    public Quality(double amount, Weight weight) {
        this.amount = BigInteger.valueOf(Math.round(amount * 1000));
        this.weight = weight;
    }

    public Quality(long amount, Weight weight) {
        this.amount = BigInteger.valueOf(amount * 1000);
        this.weight = weight;
    }

    public static Quality metric(double amount) {
        return new Quality(amount, Weight.METRIC);
    }

    void assertSameDistanceAs(Quality arg) {
        Preconditions.checkState(Objects.equal(this.weight, arg.weight));
    }

    @Override
    public int compareTo(Object arg) {
        Quality qualityArg = (Quality) arg;
        assertSameDistanceAs(qualityArg);
        return amount.compareTo(qualityArg.amount);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Length{");
        sb.append("amount=").append(amount);
        sb.append(", distance=").append(weight);
        sb.append('}');
        return sb.toString();
    }
}
