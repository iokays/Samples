package effectivejava.chapter7.item43;

import com.google.common.collect.Lists;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

// Frequency table implemented with map.merge, using lambda and method reference (Page 197)
public class Freq {
    public static void main(String[] args) {
        Map<String, Integer> frequencyTable = new TreeMap<>();

        for (String s : args)
            frequencyTable.merge(s, 1, (count, incr) -> count + incr); // Lambda
        System.out.println(frequencyTable);

        frequencyTable.clear();
        for (String s : args)
            frequencyTable.merge(s, 1, Integer::sum); // Method reference
        System.out.println(frequencyTable);

        final var list= Lists.newArrayList(LocalDate.of(2021, 01, 01), LocalDate.of(2022, 01, 01));
        list.sort(Comparator.comparing(LocalDate.now()::compareTo));

        System.out.println(list);

        System.out.println(Integer.MAX_VALUE);
        System.out.println(Long.MAX_VALUE);

    }
}
