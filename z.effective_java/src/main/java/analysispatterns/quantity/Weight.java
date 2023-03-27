package analysispatterns.quantity;

/**
 * Weight Measure
 */
public class Weight extends Unit {

    public Weight(String name) {
        super(name);
    }

    /**
     * The Metric System
     */
    public static Weight METRIC = new Weight("METRIC");

    /**
     * The American system
     */
    public static Weight AMERICAN = new Weight("AMERICAN");
}
