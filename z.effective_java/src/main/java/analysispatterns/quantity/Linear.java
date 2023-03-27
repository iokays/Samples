package analysispatterns.quantity;

/**
 * Linear Measure
 */
public class Linear extends Unit {

    public Linear(String name) {
        super(name);
    }

    /**
     * The Metric System
     */
    public static Linear METRIC = new Linear("METRIC");

    /**
     * The American system
     */
    public static Linear AMERICAN = new Linear("AMERICAN");
}
