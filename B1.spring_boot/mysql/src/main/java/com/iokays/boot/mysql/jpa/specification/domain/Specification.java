package com.iokays.boot.mysql.jpa.specification.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Specification extends SpecificationBaseEntity {

    @NotNull
    private Integer rank;

    @Column(name = "specificationType")
    @Enumerated(EnumType.STRING)
    @NotNull
    private SpecificationType type = SpecificationType.FIELD;

    @Enumerated(EnumType.STRING)
    @NotNull
    private SpecificationOperator operator = SpecificationOperator.AND;

    @ManyToOne(fetch = FetchType.LAZY)
    private Specification parent;

    @OrderBy("rank")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "parent", fetch = FetchType.LAZY)
    private Set<Specification> children = new LinkedHashSet<>();

    public void addChild(Specification child) {
        Objects.requireNonNull(child);
        child.setParent(this);
        children.add(child);
    }

    public void addChildren(Specification... childrenSpec) {
        Objects.requireNonNull(childrenSpec);
        Stream.of(childrenSpec).forEach(this::addChild);
    }

    private void setParent(Specification parent) {
        Objects.requireNonNull(parent);
        this.parent = parent;
    }

    public SpecificationOperator getOperator() {
        return operator;
    }

    public SpecificationType getType() {
        return type;
    }


    public Stream<Specification> flattened() {
        return Stream.concat(
                Stream.of(this),
                children.stream().flatMap(Specification::flattened));
    }

    public Set<Specification> getChildren() {
        return Collections.unmodifiableSet(children);
    }

}
