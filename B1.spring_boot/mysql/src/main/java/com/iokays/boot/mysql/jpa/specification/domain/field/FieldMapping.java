package com.iokays.boot.mysql.jpa.specification.domain.field;

import com.iokays.boot.mysql.jpa.specification.domain.SpecificationBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class FieldMapping extends SpecificationBaseEntity {

    @NotBlank
    @Column(unique = true)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private FieldType type = FieldType.TEXT;

    @NotBlank
    private String displayName;

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public FieldType getType() {
        return type;
    }

}