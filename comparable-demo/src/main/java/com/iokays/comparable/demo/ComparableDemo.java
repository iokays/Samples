package com.iokays.comparable.demo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

public final class ComparableDemo {
    private ComparableDemo() {
    }

    public static void main(String[] args) {
        runNaturalOrderingDemo();
        System.out.println();
        runTreeSetDemo();
        System.out.println();
        runCompareToEqualsMismatchDemo();
    }

    private static void runNaturalOrderingDemo() {
        List<PhoneNumber> numbers = new ArrayList<>(List.of(
                new PhoneNumber(707, 867, 5309),
                new PhoneNumber(10, 123, 4567),
                new PhoneNumber(415, 222, 1000)
        ));
        numbers.sort(null);

        System.out.println("[natural-order]");
        System.out.println("sortedNumbers = " + numbers);
        System.out.println("firstNumber = " + numbers.getFirst());
    }

    private static void runTreeSetDemo() {
        TreeSet<PhoneNumber> numbers = new TreeSet<>();
        numbers.add(new PhoneNumber(707, 867, 5309));
        numbers.add(new PhoneNumber(10, 123, 4567));
        numbers.add(new PhoneNumber(707, 867, 5309));

        System.out.println("[treeset]");
        System.out.println("treeSetSize = " + numbers.size());
        System.out.println("treeSet = " + numbers);
    }

    private static void runCompareToEqualsMismatchDemo() {
        PriceQuote a = new PriceQuote("1.0");
        PriceQuote b = new PriceQuote("1.00");

        HashSet<PriceQuote> hashSet = new HashSet<>();
        hashSet.add(a);
        hashSet.add(b);

        TreeSet<PriceQuote> treeSet = new TreeSet<>();
        treeSet.add(a);
        treeSet.add(b);

        System.out.println("[compareto-vs-equals]");
        System.out.println("a.equals(b) = " + a.equals(b));
        System.out.println("a.compareTo(b) = " + a.compareTo(b));
        System.out.println("hashSetSize = " + hashSet.size());
        System.out.println("treeSetSize = " + treeSet.size());
    }
}
