package com.iokays.sample.builder.hierarchy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static com.iokays.sample.builder.hierarchy.Pizza.Topping.*;
import static com.iokays.sample.builder.hierarchy.NyPizza.Size.*;

/**
 * 测试类层次结构的 Builder 模式
 */
class PizzaTest {

    @Test
    void testNyPizza() {
        NyPizza pizza = new NyPizza.Builder(SMALL)
                .addTopping(SAUSAGE)
                .addTopping(ONION)
                .build();
        
        assertNotNull(pizza);
        assertEquals(2, pizza.toppings.size());
        assertTrue(pizza.toppings.contains(SAUSAGE));
        assertTrue(pizza.toppings.contains(ONION));
    }

    @Test
    void testNyPizzaLarge() {
        NyPizza pizza = new NyPizza.Builder(LARGE)
                .addTopping(HAM)
                .addTopping(MUSHROOM)
                .addTopping(ONION)
                .build();
        
        assertNotNull(pizza);
        assertEquals(LARGE, extractSize(pizza));
        assertEquals(3, pizza.toppings.size());
    }

    @Test
    void testCalzoneWithSauceInside() {
        Calzone calzone = new Calzone.Builder()
                .addTopping(HAM)
                .sauceInside()
                .build();
        
        assertNotNull(calzone);
        assertTrue(calzone.toppings.contains(HAM));
        assertTrue(calzone.toString().contains("sauce inside"));
    }

    @Test
    void testCalzoneWithSauceOutside() {
        Calzone calzone = new Calzone.Builder()
                .addTopping(HAM)
                .addTopping(PEPPER)
                .build();
        
        assertNotNull(calzone);
        assertEquals(2, calzone.toppings.size());
        assertTrue(calzone.toString().contains("sauce outside"));
    }

    @Test
    void testPizzaToppingsDefensiveCopy() {
        NyPizza.Builder builder = new NyPizza.Builder(SMALL);
        builder.addTopping(HAM);
        NyPizza pizza = builder.build();
        
        // 验证防御性拷贝
        assertEquals(1, pizza.toppings.size());
    }

    @Test
    void testMultipleToppings() {
        NyPizza pizza = new NyPizza.Builder(MEDIUM)
                .addTopping(HAM)
                .addTopping(MUSHROOM)
                .addTopping(ONION)
                .addTopping(PEPPER)
                .addTopping(SAUSAGE)
                .build();
        
        assertEquals(5, pizza.toppings.size());
        assertTrue(pizza.toppings.contains(HAM));
        assertTrue(pizza.toppings.contains(MUSHROOM));
        assertTrue(pizza.toppings.contains(ONION));
        assertTrue(pizza.toppings.contains(PEPPER));
        assertTrue(pizza.toppings.contains(SAUSAGE));
    }

    @Test
    void testToString() {
        NyPizza pizza = new NyPizza.Builder(SMALL)
                .addTopping(HAM)
                .build();
        
        String str = pizza.toString();
        assertTrue(str.contains("New York Style"));
        assertTrue(str.contains("HAM"));
    }

    // Helper method to extract size from NyPizza
    private NyPizza.Size extractSize(NyPizza pizza) {
        String str = pizza.toString();
        if (str.contains("SMALL")) return SMALL;
        if (str.contains("MEDIUM")) return MEDIUM;
        if (str.contains("LARGE")) return LARGE;
        return null;
    }
}
