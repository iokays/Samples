package com.example.factory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;
import java.util.EnumSet;

/**
 * EnumSetExample 测试类
 */
@DisplayName("EnumSetExample 测试")
class EnumSetExampleTest {
    
    @Test
    @DisplayName("SmallEnum 的 EnumSet 应该返回 RegularEnumSet")
    void testSmallEnumSetReturnsRegularEnumSet() {
        Set<EnumSetExample.SmallEnum> smallSet = EnumSet.of(
            EnumSetExample.SmallEnum.A,
            EnumSetExample.SmallEnum.B
        );
        
        assertNotNull(smallSet);
        assertEquals(2, smallSet.size());
        assertTrue(smallSet.contains(EnumSetExample.SmallEnum.A));
        assertTrue(smallSet.contains(EnumSetExample.SmallEnum.B));
    }
    
    @Test
    @DisplayName("LargeEnum 的 EnumSet 应该返回 JumboEnumSet")
    void testLargeEnumSetReturnsJumboEnumSet() {
        Set<EnumSetExample.LargeEnum> largeSet = EnumSet.of(
            EnumSetExample.LargeEnum.E1,
            EnumSetExample.LargeEnum.E2,
            EnumSetExample.LargeEnum.E65
        );
        
        assertNotNull(largeSet);
        assertEquals(3, largeSet.size());
        assertTrue(largeSet.contains(EnumSetExample.LargeEnum.E1));
        assertTrue(largeSet.contains(EnumSetExample.LargeEnum.E65));
    }
    
    @Test
    @DisplayName("EnumSet.of() 应该正确处理单个元素")
    void testEnumSetOfSingleElement() {
        Set<EnumSetExample.SmallEnum> singleElementSet = EnumSet.of(EnumSetExample.SmallEnum.A);
        
        assertNotNull(singleElementSet);
        assertEquals(1, singleElementSet.size());
        assertTrue(singleElementSet.contains(EnumSetExample.SmallEnum.A));
    }
    
    @Test
    @DisplayName("EnumSet.of() 应该正确处理多个元素")
    void testEnumSetOfMultipleElements() {
        Set<EnumSetExample.SmallEnum> multiElementSet = EnumSet.of(
            EnumSetExample.SmallEnum.A,
            EnumSetExample.SmallEnum.C,
            EnumSetExample.SmallEnum.E
        );
        
        assertNotNull(multiElementSet);
        assertEquals(3, multiElementSet.size());
        assertTrue(multiElementSet.contains(EnumSetExample.SmallEnum.A));
        assertTrue(multiElementSet.contains(EnumSetExample.SmallEnum.C));
        assertTrue(multiElementSet.contains(EnumSetExample.SmallEnum.E));
    }
}
