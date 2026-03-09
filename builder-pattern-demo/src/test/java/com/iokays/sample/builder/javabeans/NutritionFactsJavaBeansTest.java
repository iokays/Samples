package com.iokays.sample.builder.javabeans;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 测试 JavaBeans 模式
 */
class NutritionFactsJavaBeansTest {

    @Test
    void testSetters() {
        NutritionFactsJavaBeans cocaCola = new NutritionFactsJavaBeans();
        cocaCola.setServingSize(240);
        cocaCola.setServings(8);
        cocaCola.setCalories(100);
        cocaCola.setSodium(35);
        cocaCola.setCarbohydrate(27);
        
        assertEquals(240, cocaCola.getServingSize());
        assertEquals(8, cocaCola.getServings());
        assertEquals(100, cocaCola.getCalories());
        assertEquals(0, cocaCola.getFat());
        assertEquals(35, cocaCola.getSodium());
        assertEquals(27, cocaCola.getCarbohydrate());
    }

    @Test
    void testDefaultValues() {
        NutritionFactsJavaBeans facts = new NutritionFactsJavaBeans();
        
        assertEquals(-1, facts.getServingSize());  // Required, no default
        assertEquals(-1, facts.getServings());     // Required, no default
        assertEquals(0, facts.getCalories());      // Optional, default 0
        assertEquals(0, facts.getFat());           // Optional, default 0
        assertEquals(0, facts.getSodium());        // Optional, default 0
        assertEquals(0, facts.getCarbohydrate());  // Optional, default 0
    }

    @Test
    void testPartialSettings() {
        NutritionFactsJavaBeans facts = new NutritionFactsJavaBeans();
        facts.setServingSize(240);
        facts.setServings(8);
        
        assertEquals(240, facts.getServingSize());
        assertEquals(8, facts.getServings());
        assertEquals(0, facts.getCalories());
    }

    @Test
    void testToString() {
        NutritionFactsJavaBeans facts = new NutritionFactsJavaBeans();
        facts.setServingSize(240);
        facts.setServings(8);
        facts.setCalories(100);
        
        String str = facts.toString();
        assertTrue(str.contains("servingSize=240"));
        assertTrue(str.contains("servings=8"));
        assertTrue(str.contains("calories=100"));
    }
}
