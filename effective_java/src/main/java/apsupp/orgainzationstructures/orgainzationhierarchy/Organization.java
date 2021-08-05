package apsupp.orgainzationstructures.orgainzationhierarchy;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Organization implements Serializable {

    private static Map<String, Organization> instances = Maps.newHashMap();

    private String name;

    private Organization parent;

    public Organization(String name, Organization parent) {
        this.name = name;
        this.parent = parent;
    }

    public void register() {
        instances.put(name, this);
    }

    static void cleanRegistry() {
        instances = Maps.newHashMap();
    }

    static Organization get(String name) {
        return instances.get(name);
    }

    Organization getParent() {
        return parent;
    }

    void setParent(final Organization arg) {
        this.assertValidParent(arg);
        this.parent = arg;
    }

    void assertValidParent(final Organization parent) {
        if (null != parent) {
            Preconditions.checkState(!parent.getAncestors().contains(this));
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
