package com.iokays.dependencyinjection;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * 马赛克工厂 - 演示工厂方法模式的依赖注入
 * 
 * <p>依赖注入的一个有用变体是将资源工厂传递给构造器。
 * 工厂是可以重复调用以创建类型实例的对象。
 * 
 * <p>Java 8 引入的 {@code Supplier<T>} 接口非常适合代表工厂。
 * 这种方式允许客户端传入一个工厂，来创建指定类型的任意子类型。
 * 
 * <p>这是工厂方法模式（Factory Method Pattern）的体现。
 */
public class MosaicFactory {
    
    private final Supplier<? extends Tile> tileFactory;
    
    /**
     * 构造器注入 - 接收瓷砖工厂
     * 
     * <p>使用 Supplier<T> 作为工厂接口，客户端可以传入任意类型的瓷砖创建逻辑。
     * 例如：
     * <pre>{@code
     * // 使用普通瓷砖
     * MosaicFactory factory1 = new MosaicFactory(PlainTile::new);
     * 
     * // 使用彩色瓷砖
     * MosaicFactory factory2 = new MosaicFactory(() -> new ColoredTile("blue"));
     * 
     * // 使用自定义瓷砖
     * MosaicFactory factory3 = new MosaicFactory(() -> new CustomTile("pattern1"));
     * }</pre>
     * 
     * @param tileFactory 瓷砖工厂（不能为 null）
     * @throws NullPointerException 如果 tileFactory 为 null
     */
    public MosaicFactory(Supplier<? extends Tile> tileFactory) {
        this.tileFactory = Objects.requireNonNull(tileFactory, "tileFactory must not be null");
    }
    
    /**
     * 创建马赛克图案
     * 
     * @param rows 行数
     * @param cols 列数
     * @return 马赛克图案字符串
     */
    public String createMosaic(int rows, int cols) {
        StringBuilder mosaic = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Tile tile = tileFactory.get(); // 使用工厂创建瓷砖
                mosaic.append(tile.getSymbol());
                if (j < cols - 1) {
                    mosaic.append(" ");
                }
            }
            mosaic.append("\n");
        }
        return mosaic.toString();
    }
    
    /**
     * 获取工厂描述
     * 
     * @return 工厂描述信息
     */
    public String getFactoryDescription() {
        Tile sampleTile = tileFactory.get();
        return "MosaicFactory creating " + sampleTile.getClass().getSimpleName();
    }
}

/**
 * 瓷砖接口
 */
interface Tile {
    /**
     * 获取瓷砖的符号表示
     * 
     * @return 符号字符串
     */
    String getSymbol();
}

/**
 * 普通瓷砖
 */
class PlainTile implements Tile {
    @Override
    public String getSymbol() {
        return "□";
    }
}

/**
 * 彩色瓷砖
 */
class ColoredTile implements Tile {
    private final String color;
    
    public ColoredTile(String color) {
        this.color = color;
    }
    
    @Override
    public String getSymbol() {
        return switch (color.toLowerCase()) {
            case "red" -> "■";
            case "blue" -> "▪";
            case "green" -> "▢";
            default -> "□";
        };
    }
}

/**
 * 自定义瓷砖
 */
class CustomTile implements Tile {
    private final String pattern;
    
    public CustomTile(String pattern) {
        this.pattern = pattern;
    }
    
    @Override
    public String getSymbol() {
        return switch (pattern) {
            case "pattern1" -> "◆";
            case "pattern2" -> "◇";
            default -> "□";
        };
    }
}
