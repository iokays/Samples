package com.iokays.sample.model;

/**
 * Request record for currency conversion.
 */
public record CurrencyRequest(String fromCurrency, String toCurrency) {}
