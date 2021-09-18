package analysispatterns.temporalproperty;

import analysispatterns.timerecord.MfDate;

public interface TemporalCollection {
    Object get(MfDate when);

    void put(MfDate at, Object item);

    Object get();

    void put(Object item);

}
