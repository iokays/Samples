package analysispatterns.temporalproperty;

import analysispatterns.dualtimerecord.MfDate;
import com.google.common.collect.Maps;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public interface TemporalCollection {
    Object get(MfDate when);

    void put(MfDate at, Object item);

    Object get();

    void put(Object item);

}
