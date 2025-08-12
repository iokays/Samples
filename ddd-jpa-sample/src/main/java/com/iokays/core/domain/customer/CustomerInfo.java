package com.iokays.core.domain.customer;

public record CustomerInfo(CustomerCode customerCode, FullName fullName, Gender gender, EmailAddress emailAddress) {
}
