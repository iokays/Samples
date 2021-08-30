package analysispatterns.temporalproperty;

import analysispatterns.dualtimerecord.MfDate;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class BitemporalCollection implements TemporalCollection {

    private SingleTemporalCollection contents = new SingleTemporalCollection();

    public BitemporalCollection() {
        contents.put(MfDate.today(), new SingleTemporalCollection());
    }

    public Object get(MfDate validDate, MfDate transactionDate) {
        return this.validHistoryAt(transactionDate).get(validDate);
    }

    private TemporalCollection validHistoryAt(MfDate transactionDate) {
        return (TemporalCollection) contents.get(transactionDate);
    }

    @Override
    public Object get(MfDate when) {
        return this.currentValidHistory().get(when);
    }

    @Override
    public void put(MfDate at, Object item) {
        contents.put(MfDate.today(), currentValidHistory().copy());
        currentValidHistory().put(at, item);
    }

    @Override
    public Object get() {
        return get(MfDate.today());
    }

    @Override
    public void put(Object item) {
        put(MfDate.today(), item);
    }

    private SingleTemporalCollection currentValidHistory() {
        return (SingleTemporalCollection) contents.get();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
