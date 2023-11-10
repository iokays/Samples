package com.iokays.boot.mysql.jpa.specification.domain;

import jakarta.persistence.*;

import java.util.*;

@Entity
public class Subscription extends SpecificationBaseEntity {

    @Column(unique = true)
    private Long customerId;

    @OrderBy("rank")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Specification> specifications = new LinkedHashSet<>();

    public void addSpecification(Specification... specifications) {
        Objects.requireNonNull(specifications);
        this.specifications.addAll(Arrays.asList(specifications));
    }

    public void removeSpecification(Specification... specifications) {
        Objects.requireNonNull(specifications);
        Arrays.asList(specifications).forEach(this.specifications::remove);
    }

    public void changeSpecification(Specification... specifications) {
        removeSpecification(specifications);
        addSpecification(specifications);
    }

    public Set<Specification> getSpecifications() {
        return Collections.unmodifiableSet(specifications);
    }
}