package com.iokays.boot.mysql.jpa.specification.domain.field;

import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
public class DecimalFieldContent extends FieldContent<BigDecimal> {
}