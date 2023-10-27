package com.iokays.onjava.sequencedcollections;

import com.google.common.collect.Sets;
import org.junit.jupiter.api.Test;

import java.util.SequencedCollection;

public class Tester {

    @Test
    public void test() {
        SequencedCollection<String> sc = Sets.newLinkedHashSet();
        sc.addLast("b");
        sc.addLast("c");
        sc.addFirst("a");
        System.out.println(sc);
    }

}
