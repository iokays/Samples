package com.iokays.sample.builder.builder;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 测试 Builder 模式
 */
class NutritionFactsTest {

    @Test
    void testBuilderWithAllParameters() {
        NutritionFacts cocaCola = new NutritionFacts.Builder(240, 8)
                .calories(100)
                .sodium(35)
                .carbohydrate(27)
                .build();
        
        assertEquals(240, cocaCola.getServingSize());
        assertEquals(8, cocaCola.getServings());
        assertEquals(100, cocaCola.getCalories());
        assertEquals(0, cocaCola.getFat());
        assertEquals(35, cocaCola.getSodium());
        assertEquals(27, cocaCola.getCarbohydrate());
    }

    @Test
    void testBuilderRequiredOnly() {
        NutritionFacts facts = new NutritionFacts.Builder(240, 8).build();
        
        assertEquals(240, facts.getServingSize());
        assertEquals(8, facts.getServings());
        assertEquals(0, facts.getCalories());
        assertEquals(0, facts.getFat());
        assertEquals(0, facts.getSodium());
        assertEquals(0, facts.getCarbohydrate());
    }

    @Test
    void testBuilderChainedCalls() {
        NutritionFacts facts = new NutritionFacts.Builder(240, 8)
                .calories(100)
                .fat(10)
                .sodium(20)
                .carbohydrate(30)
                .build();
        
        assertEquals(100, facts.getCalories());
        assertEquals(10, facts.getFat());
        assertEquals(20, facts.getSodium());
        assertEquals(30, facts.getCarbohydrate());
    }

    @Test
    void testImmutability() {
        NutritionFacts.Builder builder = new NutritionFacts.Builder(240, 8);
        NutritionFacts facts1 = builder.calories(100).build();
        NutritionFacts facts2 = builder.calories(200).build();
        
        // 每次调用 build() 都会创建新对象
        assertEquals(100, facts1.getCalories());
        assertEquals(200, facts2.getCalories());
    }

    @Test
    void testToString() {
        NutritionFacts facts = new NutritionFacts.Builder(240, 8)
                .calories(100)
                .build();
        
        String str = facts.toString();
        assertTrue(str.contains("servingSize=240"));
        assertTrue(str.contains("servings=8"));
        assertTrue(str.contains("calories=100"));
    }
}
