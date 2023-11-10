package com.iokays.boot.mysql.jpa.specification.domain.field;

import com.iokays.boot.mysql.jpa.specification.domain.Specification;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class FieldSpecification extends Specification {

    @ManyToOne
    @NotNull
    private FieldMapping fieldMapping = new FieldMapping();

    @Enumerated(EnumType.STRING) @NotNull
    private FieldOperator fieldOperator = FieldOperator.EQUALS;

    @NotNull
    @Column(name = "expected_content_type")
    @JoinColumn(name = "expectedContentId")
    @org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.ALL)
    @org.hibernate.annotations.Any(optional = false)
    @org.hibernate.annotations.AnyDiscriminatorValue(discriminator = FieldType.Constants.TEXT_TYPE, entity = TextFieldContent.class)
    @org.hibernate.annotations.AnyDiscriminatorValue(discriminator = FieldType.Constants.INTEGER_TYPE, entity = IntegerFieldContent.class)
    @org.hibernate.annotations.AnyDiscriminatorValue(discriminator = FieldType.Constants.DECIMAL_TYPE, entity = DecimalFieldContent.class)
    @org.hibernate.annotations.AnyDiscriminatorValue(discriminator = FieldType.Constants.CURRENCY_TYPE, entity = CurrencyFieldContent.class)
    @org.hibernate.annotations.AnyDiscriminatorValue(discriminator = FieldType.Constants.DATE_TYPE, entity = DateFieldContent.class)
    private FieldContent<?> expectedContent = new TextFieldContent();

    @Override
    public String toString() {
        return fieldMapping.getName() + " [" + fieldOperator + "] " + expectedContent;
    }

    public FieldMapping getFieldMapping() {
        return fieldMapping;
    }

    public FieldOperator getFieldOperator() {
        return fieldOperator;
    }

    public FieldContent getExpectedContent() {
        return expectedContent;
    }

}