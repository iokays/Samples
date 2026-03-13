package com.iokays.tostring.demo;

import java.util.List;
import java.util.Map;

public final class ToStringDemo {
    private ToStringDemo() {
    }

    public static void main(String[] args) {
        var legacyPotion = new PotionWithoutToString(9, "love", "turpentine", "india ink");
        var potion = new Potion(9, "love", "turpentine", "india ink");
        var supportLine = new PhoneNumber(10, 123, 4567);
        var emergencyLine = new PhoneNumber(707, 867, 5309);

        System.out.println("[default-object]");
        System.out.println("legacyPotion = " + legacyPotion);
        System.out.println();

        System.out.println("[purposeful-object]");
        System.out.println("potion = " + potion);
        System.out.println();

        System.out.println("[formatted-value]");
        System.out.println("supportLine = " + supportLine);
        System.out.println("roundTrip = " + supportLine.equals(PhoneNumber.parse(supportLine.toString())));
        System.out.println();

        System.out.println("[collection-diagnostics]");
        System.out.println("contacts = " + Map.of("support", supportLine, "emergency", emergencyLine));
        System.out.println("potions = " + List.of(potion));
    }
}
