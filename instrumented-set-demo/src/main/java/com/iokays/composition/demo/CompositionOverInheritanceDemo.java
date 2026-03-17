package com.iokays.composition.demo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public final class CompositionOverInheritanceDemo {
    public static void main(String[] args) {
        List<String> cereals = List.of("Snap", "Crackle", "Pop");

        BrokenInstrumentedHashSet<String> broken = new BrokenInstrumentedHashSet<>();
        broken.addAll(cereals);

        InstrumentedSet<String> fixed = new InstrumentedSet<>(new HashSet<>());
        fixed.addAll(cereals);

        InstrumentedSet<String> wrappedTreeSet = new InstrumentedSet<>(new TreeSet<>());
        wrappedTreeSet.addAll(cereals);

        System.out.println("[inheritance]");
        System.out.println("addCount = " + broken.getAddCount());
        System.out.println("size = " + broken.size());
        System.out.println("contents = " + broken);
        System.out.println();

        System.out.println("[composition]");
        System.out.println("addCount = " + fixed.getAddCount());
        System.out.println("size = " + fixed.size());
        System.out.println("contents = " + fixed);
        System.out.println();

        System.out.println("[wrapper-flexibility]");
        System.out.println("wrappedType = " + wrappedTreeSet.getClass().getSimpleName());
        System.out.println("addCount = " + wrappedTreeSet.getAddCount());
        System.out.println("first = " + wrappedTreeSet.iterator().next());
        System.out.println("contents = " + wrappedTreeSet);
    }
}
