package analysispatterns.organizationhierarchy;

import analysispatterns.name.NamedObject;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Organization extends NamedObject {

    private static Map<String, Organization> instances = Maps.newHashMap();

    private Organization parent;

    public Organization(String aName, Organization aParent) {
        super(aName);
        this.setParent(aParent);
        this.register();
    }

    public void register() {
        instances.put(this.name(), this);
    }

    static void cleanRegistry() {
        instances = Maps.newHashMap();
    }

    static Organization get(String aName) {
        return instances.get(aName);
    }

    Organization getParent() {
        return parent;
    }

    void setParent(final Organization arg) {
        this.assertValidParent(arg);
        this.parent = arg;
    }

    void assertValidParent(final Organization aParent) {
        if (null != aParent) {
            Preconditions.checkState(!aParent.getAncestors().contains(this));
        }
    }

    /**
     * 获取祖先
     * @return
     */
    public Set<Organization> getAncestors() {
        final Set<Organization> result = Sets.newHashSet();
        if (null != this.getParent()) {
            result.add(this.getParent());
            result.addAll(this.getParent().getAncestors());
        }
        return result;
    }

    /**
     *  获取子孙
     * @return
     */
    public Set<Organization> getDescendents() {
        final Set<Organization> result = Sets.newHashSet();
        if (CollectionUtils.isNotEmpty(this.getChildren())) {
            result.addAll(this.getChildren());
            this.getChildren().forEach(v -> result.addAll(v.getDescendents()));
        }
        return result;
    }

    /**
     * 获取兄弟姊妹
     * @return
     */
    public Set<Organization> getSiblings() {
        final Set<Organization> result = this.getParent().getChildren();
        result.remove(this);
        return result;
    }

    Set<Organization> getChildren() {
        return instances.values().stream()
                .filter(v -> null != v.getParent())
                .filter(v -> v.getParent().equals(this))
                .collect(Collectors.toSet());
    }

}
