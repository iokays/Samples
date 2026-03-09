package com.iokays.sample.builder.telescoping;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 测试可伸缩构造方法模式
 */
class NutritionFactsTelescopingTest {

    @Test
    void testRequiredParametersOnly() {
        NutritionFactsTelescoping facts = new NutritionFactsTelescoping(240, 8);
        
        assertEquals(240, facts.getServingSize());
        assertEquals(8, facts.getServings());
        assertEquals(0, facts.getCalories());
        assertEquals(0, facts.getFat());
        assertEquals(0, facts.getSodium());
        assertEquals(0, facts.getCarbohydrate());
    }

    @Test
    void testAllParameters() {
        NutritionFactsTelescoping cocaCola = new NutritionFactsTelescoping(240, 8, 100, 0, 35, 27);
        
        assertEquals(240, cocaCola.getServingSize());
        assertEquals(8, cocaCola.getServings());
        assertEquals(100, cocaCola.getCalories());
        assertEquals(0, cocaCola.getFat());
        assertEquals(35, cocaCola.getSodium());
        assertEquals(27, cocaCola.getCarbohydrate());
    }

    @Test
    void testPartialParameters() {
        NutritionFactsTelescoping facts = new NutritionFactsTelescoping(240, 8, 100);
        
        assertEquals(240, facts.getServingSize());
        assertEquals(8, facts.getServings());
        assertEquals(100, facts.getCalories());
        assertEquals(0, facts.getFat());
    }

    @Test
    void testToString() {
        NutritionFactsTelescoping facts = new NutritionFactsTelescoping(240, 8, 100, 0, 35, 27);
        String str = facts.toString();
        
        assertTrue(str.contains("servingSize=240"));
        assertTrue(str.contains("servings=8"));
        assertTrue(str.contains("calories=100"));
    }
}
