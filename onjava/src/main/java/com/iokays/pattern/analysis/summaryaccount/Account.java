package com.iokays.pattern.analysis.summaryaccount;

import com.google.common.collect.Sets;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;

import java.math.BigDecimal;
import java.util.Set;

public abstract class Account {

    private Account parent;
    private final Set<Account> children = Sets.newHashSet();

    public Account(Account parent) {
        this.setParent(parent);
    }


    private void addChildren(final Account account) {
        this.children.add(account);
    }

    public Account getParent() {
        return parent;
    }

    void setParent(final Account parent) {
        this.assertValidParent(parent);
        this.parent = parent;
        if (null != this.parent) {
            this.parent.addChildren(this);
        }
    }

    void assertValidParent(final Account aParent) {
        if (null != aParent) {
            Preconditions.checkState(!aParent.getAncestors().contains(this));
        }
    }

    /**
     * 获取祖先
     * @return
     */
    public Set<Account> getAncestors() {
        final Set<Account> result = Sets.newHashSet();
        if (null != this.getParent()) {
            result.add(this.getParent());
            result.addAll(this.getParent().getAncestors());
        }
        return result;
    }

    public Set<Account> getChildren() {
        return children;
    }

    /**
     *  获取子孙
     * @return
     */
    public Set<Account> getDescendents() {
        final Set<Account> result = Sets.newHashSet();
        if (CollectionUtils.isNotEmpty(this.getChildren())) {
            result.addAll(this.getChildren());
            this.getChildren().forEach(v -> result.addAll(v.getDescendents()));
        }
        return result;
    }


    public abstract BigDecimal balance();

}
