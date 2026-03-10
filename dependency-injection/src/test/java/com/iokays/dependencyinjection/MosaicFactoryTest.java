package com.iokays.dependencyinjection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 马赛克工厂测试 - 验证工厂模式的依赖注入
 * 
 * <p>这些测试展示了如何使用 Supplier<T> 作为工厂接口，
 * 实现灵活的资源创建。
 */
@DisplayName("马赛克工厂测试")
class MosaicFactoryTest {
    
    @Test
    @DisplayName("应该使用普通瓷砖创建马赛克")
    void shouldCreateMosaicWithPlainTiles() {
        // 创建使用普通瓷砖的工厂
        MosaicFactory factory = new MosaicFactory(PlainTile::new);
        
        // 创建 3x3 马赛克
        String mosaic = factory.createMosaic(3, 3);
        
        // 验证输出
        assertNotNull(mosaic);
        String[] lines = mosaic.split("\n");
        assertEquals(3, lines.length);
        assertTrue(mosaic.contains("□"));
    }
    
    @Test
    @DisplayName("应该使用彩色瓷砖创建马赛克")
    void shouldCreateMosaicWithColoredTiles() {
        // 创建使用蓝色瓷砖的工厂
        MosaicFactory factory = new MosaicFactory(() -> new ColoredTile("blue"));
        
        String mosaic = factory.createMosaic(2, 2);
        
        assertNotNull(mosaic);
        assertTrue(mosaic.contains("▪")); // 蓝色瓷砖符号
    }
    
    @Test
    @DisplayName("应该支持不同的瓷砖类型")
    void shouldSupportDifferentTileTypes() {
        // 创建红色瓷砖工厂
        MosaicFactory redFactory = new MosaicFactory(() -> new ColoredTile("red"));
        String redMosaic = redFactory.createMosaic(1, 1);
        assertTrue(redMosaic.contains("■"));
        
        // 创建绿色瓷砖工厂
        MosaicFactory greenFactory = new MosaicFactory(() -> new ColoredTile("green"));
        String greenMosaic = greenFactory.createMosaic(1, 1);
        assertTrue(greenMosaic.contains("▢"));
        
        // 创建自定义瓷砖工厂
        MosaicFactory customFactory = new MosaicFactory(() -> new CustomTile("pattern1"));
        String customMosaic = customFactory.createMosaic(1, 1);
        assertTrue(customMosaic.contains("◆"));
    }
    
    @Test
    @DisplayName("应该动态创建瓷砖实例")
    void shouldCreateTileInstancesDynamically() {
        // 使用计数器跟踪创建次数
        final int[] creationCount = {0};
        
        // 创建计数工厂
        MosaicFactory factory = new MosaicFactory(() -> {
            creationCount[0]++;
            return new PlainTile();
        });
        
        // 创建 2x3 马赛克，应该创建 6 个瓷砖
        factory.createMosaic(2, 3);
        
        assertEquals(6, creationCount[0]);
    }
    
    @Test
    @DisplayName("构造器应该拒绝 null 工厂")
    void shouldRejectNullFactory() {
        assertThrows(NullPointerException.class, () -> {
            new MosaicFactory(null);
        });
    }
    
    @Test
    @DisplayName("应该返回正确的工厂描述")
    void shouldReturnCorrectDescription() {
        MosaicFactory factory = new MosaicFactory(PlainTile::new);
        String description = factory.getFactoryDescription();
        
        assertTrue(description.contains("MosaicFactory"));
        assertTrue(description.contains("PlainTile"));
    }
    
    @Test
    @DisplayName("应该支持不同的马赛克尺寸")
    void shouldSupportDifferentSizes() {
        MosaicFactory factory = new MosaicFactory(PlainTile::new);
        
        // 测试单行
        String singleRow = factory.createMosaic(1, 5);
        String[] rowLines = singleRow.split("\n");
        assertEquals(1, rowLines.length);
        
        // 测试单列
        String singleCol = factory.createMosaic(5, 1);
        String[] colLines = singleCol.split("\n");
        assertEquals(5, colLines.length);
        
        // 测试正方形
        String square = factory.createMosaic(4, 4);
        String[] squareLines = square.split("\n");
        assertEquals(4, squareLines.length);
    }
}
