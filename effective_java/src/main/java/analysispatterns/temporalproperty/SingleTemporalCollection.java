package analysispatterns.temporalproperty;

import analysispatterns.dualtimerecord.MfDate;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SingleTemporalCollection implements TemporalCollection {

    private Map contents = Maps.newHashMap();

    private List _milestoneCache;

    private List milestoneCache() {
        if (null == _milestoneCache) {
            this.calculateMilestone();
        }
        return _milestoneCache;
    }

    private void calculateMilestone() {
        _milestoneCache = new ArrayList(contents.size());
        _milestoneCache.addAll(contents.keySet());
        Collections.sort(_milestoneCache, Collections.reverseOrder());
    }

    private void clearMilestoneCache() {
        _milestoneCache = null;
    }

    @Override
    public Object get() {
        return this.get(MfDate.today());
    }

    @Override
    public void put(Object item) {
        put(MfDate.today(), item);
    }

    @Override
    public Object get(MfDate when) {
        final var it = milestoneCache().iterator();
        while (it.hasNext()) {
            final var thisDate = (MfDate) it.next();

            if (thisDate.before(when) || thisDate.equals(when)) {
                return contents.get(thisDate);
            }
        }
        throw new IllegalArgumentException("no records that early");
    }

    @Override
    public void put(MfDate at, Object item) {
        this.contents.put(at, item);
        this.clearMilestoneCache();
    }

    public TemporalCollection copy() {
        SingleTemporalCollection result = new SingleTemporalCollection();
        result.contents.putAll(this.contents);
        return result;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
