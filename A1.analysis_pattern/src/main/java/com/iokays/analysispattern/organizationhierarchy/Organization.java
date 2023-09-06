package com.iokays.analysispattern.organizationhierarchy;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Organization {

    private static Map<String, Organization> instances = Maps.newHashMap();

    private Organization parent;

    private final String name;

    protected Organization(String name, Organization parent) {
        this.name = name;
        this.setParent(parent);
    }

    /**
     * 父节点
     * @return
     */
    public Organization getParent() {
        return parent;
    }

    private void setParent(Organization arg) {
        parent = arg;
    }

    protected void assertValidParent(Organization parent) {
        if (null != parent) {
            Preconditions.checkState(!parent.getAncestors().contains(this));
        }
    }

    /**
     * 子节点
     * @return
     */
    public Set<Organization> getChildren() {
        return instances.values().stream()
                .filter(v -> null != v.getParent() && v.getParent().equals(this))
                .collect(Collectors.toSet());
    }

    /**
     * 祖先节点
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
     * 后代节点
     * @return
     */
    public Set<Organization> getDescendants() {
        final Set<Organization> result = Sets.newHashSet();
        if (null != this.getChildren()) {
            result.addAll(this.getChildren());
            this.getChildren().forEach(v -> result.addAll(v.getDescendants()));
        }
        return result;
    }

    /**
     * 兄弟节点
     * @return
     */
    public Set<Organization> getSiblings() {
        final Set<Organization> result = Sets.newHashSet();
        if (null != this.getParent()) {
            result.addAll(this.getParent().getChildren());
            result.remove(this);
        }
        return result;
    }


    public void register() {
        instances.put(name, this);
    }

    public static void clearRegister() {
        instances = Maps.newHashMap();
    }

    public static Organization get(String name) {
        return instances.get(name);
    }

}
