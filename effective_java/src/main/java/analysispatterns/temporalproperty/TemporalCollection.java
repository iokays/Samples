package analysispatterns.temporalproperty;

import analysispatterns.dualtimerecord.MfDate;
import com.google.common.collect.Maps;

import java.io.Serializable;
import java.util.*;

public class TemporalCollection implements Serializable {

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

    public Object get(MfDate when) {
        final var it = milestoneCache().iterator();
        while (it.hasNext()) {
            final var thisDate = (MfDate) it.next();

            if (thisDate.before(when) || thisDate.equals(when)) {
                return contents.get(when);
            }
        }
        throw new IllegalArgumentException("no records that early");
    }

    public void put(MfDate at, Object item) {
        this.contents.put(at, item);
        this.clearMilestoneCache();
    }


}
