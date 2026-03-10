package com.iokays.dependencyinjection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 依赖注入对比测试 - 展示依赖注入的优势
 * 
 * <p>这个测试类通过对比的方式，清晰展示：
 * <ul>
 *   <li>静态工具类和单例模式的局限性</li>
 *   <li>依赖注入如何解决这些问题</li>
 *   <li>为什么依赖注入是更好的选择</li>
 * </ul>
 */
@DisplayName("依赖注入对比测试")
class DependencyInjectionComparisonTest {
    
    @Nested
    @DisplayName("静态工具类的局限性")
    class StaticClassLimitations {
        
        @Test
        @DisplayName("静态工具类不支持多语言词典")
        void staticClassNotSupportMultipleLanguages() {
            // 静态工具类硬编码了英文词典
            assertEquals("en", SpellCheckerStatic.getDictionaryLanguage());
            
            // 无法切换到其他语言
            // 假设我们想要中文词典，这是不可能的
            assertNotEquals("zh", SpellCheckerStatic.getDictionaryLanguage());
        }
        
        @Test
        @DisplayName("静态工具类难以测试")
        void staticClassHardToTest() {
            // 静态工具类使用的词典是固定的
            // 我们无法用模拟词典替换它进行测试
            
            // 这些断言依赖于硬编码的词典内容
            assertTrue(SpellCheckerStatic.isValid("hello"));
            assertTrue(SpellCheckerStatic.isValid("world"));
            
            // 如果我们想测试边界情况（如空词典），这是不可能的
            // 如果我们想测试性能（如超大词典），这也是不可能的
        }
        
        @Test
        @DisplayName("静态工具类无法定制行为")
        void staticClassCannotCustomize() {
            // 我们无法注入自定义的词典实现
            // 所有客户端都使用同一个硬编码的词典
            
            // 这导致：
            // 1. 无法支持不同词汇表（专业词汇、测试词汇等）
            // 2. 无法针对特定场景优化词典加载
            // 3. 无法实现词典的热更新
        }
    }
    
    @Nested
    @DisplayName("单例模式的局限性")
    class SingletonLimitations {
        
        @Test
        @DisplayName("单例模式同样不支持多语言词典")
        void singletonNotSupportMultipleLanguages() {
            // 单例实例只有一个，词典在构造时固定
            assertEquals("en", SpellCheckerSingleton.INSTANCE.getDictionaryLanguage());
            
            // 无法创建不同语言的拼写检查器实例
            assertNotEquals("zh", SpellCheckerSingleton.INSTANCE.getDictionaryLanguage());
        }
        
        @Test
        @DisplayName("单例模式难以测试")
        void singletonHardToTest() {
            // 单例模式的全局状态使测试变得困难
            // 因为所有测试共享同一个实例
            
            // 这些测试依赖于单例的内部状态
            assertTrue(SpellCheckerSingleton.INSTANCE.isValid("hello"));
            
            // 如果某个测试修改了单例状态（虽然这里不可变），
            // 会影响其他测试的结果
        }
        
        @Test
        @DisplayName("单例模式限制了并发场景")
        void singletonLimitsConcurrency() {
            // 单例是全局状态的一种形式
            // 在并发环境下，这可能导致：
            // 1. 多个线程竞争同一个资源
            // 2. 难以实现线程隔离的测试
            // 3. 增加系统的复杂性
        }
    }
    
    @Nested
    @DisplayName("依赖注入的优势")
    class DependencyInjectionAdvantages {
        
        @Test
        @DisplayName("依赖注入支持多语言词典")
        void diSupportsMultipleLanguages() {
            // 可以为不同语言创建不同的拼写检查器
            Lexicon englishDict = Dictionary.english(Set.of("hello"));
            Lexicon chineseDict = Dictionary.chinese(Set.of("你好"));
            Lexicon spanishDict = new Dictionary("es", Set.of("hola"));
            
            SpellChecker englishChecker = new SpellChecker(englishDict);
            SpellChecker chineseChecker = new SpellChecker(chineseDict);
            SpellChecker spanishChecker = new SpellChecker(spanishDict);
            
            // 每个实例独立工作
            assertEquals("en", englishChecker.getDictionaryLanguage());
            assertEquals("zh", chineseChecker.getDictionaryLanguage());
            assertEquals("es", spanishChecker.getDictionaryLanguage());
            
            assertTrue(englishChecker.isValid("hello"));
            assertTrue(chineseChecker.isValid("你好"));
            assertTrue(spanishChecker.isValid("hola"));
        }
        
        @Test
        @DisplayName("依赖注入易于测试")
        void diEasyToTest() {
            // 创建测试用的模拟词典
            Lexicon mockDict = new Lexicon() {
                @Override
                public boolean contains(String word) {
                    // 模拟词典只接受特定单词
                    return "test".equals(word);
                }
                
                @Override
                public String getLanguage() {
                    return "test";
                }
            };
            
            // 注入模拟词典进行测试
            SpellChecker checker = new SpellChecker(mockDict);
            
            // 现在我们可以控制词典的行为
            assertTrue(checker.isValid("test"));
            assertFalse(checker.isValid("hello"));
            
            // 这使得单元测试变得简单和可靠
        }
        
        @Test
        @DisplayName("依赖注入支持灵活定制")
        void diSupportsCustomization() {
            // 可以创建自定义词典实现
            Lexicon customDict = new Lexicon() {
                private final Set<String> technicalTerms = Set.of(
                    "algorithm", "interface", "polymorphism"
                );
                
                @Override
                public boolean contains(String word) {
                    // 支持技术术语词典
                    return technicalTerms.contains(word.toLowerCase());
                }
                
                @Override
                public String getLanguage() {
                    return "technical-en";
                }
            };
            
            SpellChecker technicalChecker = new SpellChecker(customDict);
            
            // 验证技术术语词典
            assertTrue(technicalChecker.isValid("algorithm"));
            assertTrue(technicalChecker.isValid("interface"));
            assertFalse(technicalChecker.isValid("hello")); // 普通单词不在词典中
            
            assertEquals("technical-en", technicalChecker.getDictionaryLanguage());
        }
        
        @Test
        @DisplayName("依赖注入支持并发场景")
        void diSupportsConcurrency() {
            // 每个线程可以有自己的拼写检查器实例
            // 或者多个线程共享同一个不可变的实例
            
            Lexicon dictionary = Dictionary.english(Set.of("concurrent", "test"));
            SpellChecker sharedChecker = new SpellChecker(dictionary);
            
            // 不可变对象是线程安全的
            // 多个线程可以安全地共享这个实例
            assertDoesNotThrow(() -> {
                // 模拟并发访问
                Runnable task = () -> {
                    assertTrue(sharedChecker.isValid("concurrent"));
                    assertFalse(sharedChecker.isValid("unknown"));
                };
                
                Thread t1 = new Thread(task);
                Thread t2 = new Thread(task);
                
                t1.start();
                t2.start();
                
                t1.join();
                t2.join();
            });
        }
        
        @Test
        @DisplayName("依赖注入提高代码可读性")
        void diImprovesCodeReadability() {
            // 依赖关系清晰可见
            Lexicon dictionary = Dictionary.english(Set.of("hello", "world"));
            SpellChecker checker = new SpellChecker(dictionary);
            
            // 通过构造器参数，我们可以清楚地看到：
            // SpellChecker 依赖于 Lexicon
            // 这使得代码的依赖关系一目了然
            
            assertNotNull(checker);
            assertEquals("en", checker.getDictionaryLanguage());
        }
    }
    
    @Nested
    @DisplayName("工厂模式的优势")
    class FactoryPatternAdvantages {
        
        @Test
        @DisplayName("工厂模式支持延迟创建")
        void factorySupportsLazyCreation() {
            final boolean[] created = {false};
            
            // 创建工厂，但瓷砖尚未创建
            MosaicFactory factory = new MosaicFactory(() -> {
                created[0] = true;
                return new PlainTile();
            });
            
            // 此时瓷砖尚未创建
            assertFalse(created[0]);
            
            // 只有在需要时才创建
            factory.createMosaic(1, 1);
            
            // 现在瓷砖被创建了
            assertTrue(created[0]);
        }
        
        @Test
        @DisplayName("工厂模式支持动态选择类型")
        void factorySupportsDynamicTypeSelection() {
            // 根据条件选择不同的瓷砖类型
            String tileType = "color"; // 可以从配置或用户输入获取
            
            MosaicFactory factory = new MosaicFactory(
                "color".equals(tileType) 
                    ? () -> new ColoredTile("blue")
                    : PlainTile::new
            );
            
            String mosaic = factory.createMosaic(1, 1);
            
            // 验证使用了正确的瓷砖类型
            if ("color".equals(tileType)) {
                assertTrue(mosaic.contains("▪"));
            } else {
                assertTrue(mosaic.contains("□"));
            }
        }
        
        @Test
        @DisplayName("工厂模式与 Supplier 接口无缝集成")
        void factoryIntegratesWithSupplier() {
            // Supplier<T> 是标准的函数式接口
            // 可以轻松与其他 Java 8+ 特性结合使用
            
            // 使用方法引用
            MosaicFactory factory1 = new MosaicFactory(PlainTile::new);
            
            // 使用 Lambda 表达式
            MosaicFactory factory2 = new MosaicFactory(() -> new CustomTile("pattern1"));
            
            // 使用构造器引用（如果需要参数）
            MosaicFactory factory3 = new MosaicFactory(() -> new ColoredTile("red"));
            
            // 所有这些都创建有效的工厂
            assertNotNull(factory1.createMosaic(1, 1));
            assertNotNull(factory2.createMosaic(1, 1));
            assertNotNull(factory3.createMosaic(1, 1));
        }
    }
}
