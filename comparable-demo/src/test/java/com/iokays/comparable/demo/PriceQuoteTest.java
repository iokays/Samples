package com.iokays.comparable.demo;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PriceQuoteTest {

    @Test
    void compareToCanDisagreeWithEqualsLikeBigDecimal() {
        PriceQuote a = new PriceQuote("1.0");
        PriceQuote b = new PriceQuote("1.00");

        assertEquals(false, a.equals(b));
        assertEquals(0, a.compareTo(b));
    }

    @Test
    void hashSetAndTreeSetCanBehaveDifferentlyWhenEqualityIsInconsistent() {
        PriceQuote a = new PriceQuote("1.0");
        PriceQuote b = new PriceQuote("1.00");

        HashSet<PriceQuote> hashSet = new HashSet<>();
        hashSet.add(a);
        hashSet.add(b);

        TreeSet<PriceQuote> treeSet = new TreeSet<>();
        treeSet.add(a);
        treeSet.add(b);

        assertEquals(2, hashSet.size());
        assertEquals(1, treeSet.size());
    }
}
