package com.iokays.tostring.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PotionTest {

    @Test
    void purposefulToStringIncludesSalientFields() {
        var potion = new Potion(9, "love", "turpentine", "india ink");

        assertEquals("[Potion #9: type=love, smell=turpentine, look=india ink]", potion.toString());
    }

    @Test
    void defaultObjectToStringLeaksOnlyClassNameAndIdentityHash() {
        var legacyPotion = new PotionWithoutToString(9, "love", "turpentine", "india ink");
        var printed = legacyPotion.toString();

        assertTrue(printed.startsWith(PotionWithoutToString.class.getName() + "@"));
    }

    @Test
    void accessorsRemainAvailableEvenWhenToStringIsReadable() {
        var potion = new Potion(9, "love", "turpentine", "india ink");

        assertEquals(9, potion.id());
        assertEquals("love", potion.type());
        assertEquals("turpentine", potion.smell());
        assertEquals("india ink", potion.look());
    }
}
