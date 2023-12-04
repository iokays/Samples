package com.iokays.analysispattern.base;

import java.text.NumberFormat;
import java.util.Locale;

public class Currency extends Unit {
    public static Currency USD = new Currency("USD", Locale.US, "$");
    public static Currency DEM = new Currency("DEM", Locale.GERMANY, "DM");
    public static Currency GBP = new Currency("GBP", Locale.UK, "#");
    private Locale myLocale;
    private String mySymbol;

    public Currency(String name) {
        super(name);
    }

    public Currency(String name, String symbol) {
        super(name);
        mySymbol = symbol;
    }

    public Currency(String name, Locale locale) {
        super(name);
        myLocale = locale;
    }

    public Currency(String name, Locale locale, String symbol) {
        super(name);
        myLocale = locale;
        mySymbol = symbol;
    }

    public String formatString(double amount) {
        return mySymbol + String.valueOf(amount);
    }

    public NumberFormat getFormat() {
        return NumberFormat.getCurrencyInstance(myLocale);
    }
}