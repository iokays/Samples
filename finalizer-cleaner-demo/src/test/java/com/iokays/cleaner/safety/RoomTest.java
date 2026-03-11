package com.iokays.cleaner.safety;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Room 测试
 */
class RoomTest {
    
    @BeforeEach
    void setUp() {
        Room.reset();
    }
    
    @Test
    void testCreation() {
        Room room = new Room(5);
        assertEquals(1, room.getId());
        assertEquals(1, Room.getInstanceCount());
    }
    
    @Test
    void testExplicitClose() {
        try (Room room = new Room(7)) {
            assertNotNull(room);
        }
        
        // 显式关闭后应该被清理
        assertEquals(1, Room.getCleanedCount());
    }
    
    @Test
    void testMultipleRooms() {
        try (Room r1 = new Room(3);
             Room r2 = new Room(5);
             Room r3 = new Room(7)) {
            // 使用房间
        }
        
        assertEquals(3, Room.getInstanceCount());
        assertEquals(3, Room.getCleanedCount());
    }
    
    @Test
    void testSafetyNetUnpredictability() {
        // 这个测试展示 Cleaner 作为安全网的不可预测性
        // 安全网可能生效，也可能不生效，不可预测
        
        // 创建对象但不关闭
        new Room(99);
        
        // 建议 GC（但不保证执行）
        System.gc();
        
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // 核心问题：即使是安全网，执行时间也不可预测
        System.out.println("Cleaner 安全网已清理: " + Room.getCleanedCount());
        // 不做断言，仅展示不可预测性
    }
    
    @Test
    void testReset() {
        new Room(5);
        new Room(7);
        assertEquals(2, Room.getInstanceCount());
        
        Room.reset();
        assertEquals(0, Room.getInstanceCount());
        assertEquals(0, Room.getCleanedCount());
    }
}
