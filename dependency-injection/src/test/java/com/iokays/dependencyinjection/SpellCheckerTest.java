package com.iokays.dependencyinjection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 拼写检查器测试 - 验证依赖注入的正确性
 * 
 * <p>这些测试展示了依赖注入如何提高代码的可测试性和灵活性。
 * 我们可以轻松创建模拟词典进行测试，而无需修改 SpellChecker 的实现。
 */
@DisplayName("拼写检查器测试")
class SpellCheckerTest {
    
    @Test
    @DisplayName("构造器应该接收词典依赖")
    void shouldAcceptDictionaryDependency() {
        // 准备测试词典
        Lexicon dictionary = Dictionary.english(Set.of("hello", "world", "test"));
        
        // 创建拼写检查器
        SpellChecker checker = new SpellChecker(dictionary);
        
        // 验证词典被正确注入
        assertNotNull(checker);
        assertEquals("en", checker.getDictionaryLanguage());
    }
    
    @Test
    @DisplayName("应该正确检查有效单词")
    void shouldValidateCorrectWords() {
        // 创建包含特定单词的词典
        Lexicon dictionary = Dictionary.english(Set.of("hello", "world", "java"));
        SpellChecker checker = new SpellChecker(dictionary);
        
        // 验证有效单词
        assertTrue(checker.isValid("hello"));
        assertTrue(checker.isValid("world"));
        assertTrue(checker.isValid("java"));
        
        // 验证无效单词
        assertFalse(checker.isValid("unknown"));
        assertFalse(checker.isValid("python"));
    }
    
    @Test
    @DisplayName("应该支持不同语言的词典")
    void shouldSupportDifferentLanguages() {
        // 创建英文拼写检查器
        Lexicon englishDictionary = Dictionary.english(Set.of("hello", "world"));
        SpellChecker englishChecker = new SpellChecker(englishDictionary);
        
        // 创建中文拼写检查器
        Lexicon chineseDictionary = Dictionary.chinese(Set.of("你好", "世界"));
        SpellChecker chineseChecker = new SpellChecker(chineseDictionary);
        
        // 验证语言隔离
        assertEquals("en", englishChecker.getDictionaryLanguage());
        assertEquals("zh", chineseChecker.getDictionaryLanguage());
        
        assertTrue(englishChecker.isValid("hello"));
        assertFalse(englishChecker.isValid("你好"));
        
        assertTrue(chineseChecker.isValid("你好"));
        assertFalse(chineseChecker.isValid("hello"));
    }
    
    @Test
    @DisplayName("应该支持自定义词典实现")
    void shouldSupportCustomDictionaryImplementation() {
        // 创建模拟词典（用于测试）
        Lexicon mockDictionary = new Lexicon() {
            @Override
            public boolean contains(String word) {
                // 只接受特定格式的单词
                return word != null && word.startsWith("test");
            }
            
            @Override
            public String getLanguage() {
                return "mock";
            }
        };
        
        SpellChecker checker = new SpellChecker(mockDictionary);
        
        // 验证自定义词典行为
        assertTrue(checker.isValid("test123"));
        assertTrue(checker.isValid("testing"));
        assertFalse(checker.isValid("hello"));
        assertEquals("mock", checker.getDictionaryLanguage());
    }
    
    @Test
    @DisplayName("构造器应该拒绝 null 词典")
    void shouldRejectNullDictionary() {
        // 验证 null 检查
        assertThrows(NullPointerException.class, () -> {
            new SpellChecker(null);
        });
    }
    
    @Test
    @DisplayName("词典检查应该不区分大小写")
    void shouldBeCaseInsensitive() {
        Lexicon dictionary = Dictionary.english(Set.of("Hello", "WORLD"));
        SpellChecker checker = new SpellChecker(dictionary);
        
        // 验证大小写不敏感
        assertTrue(checker.isValid("hello"));
        assertTrue(checker.isValid("HELLO"));
        assertTrue(checker.isValid("world"));
        assertTrue(checker.isValid("World"));
    }
    
    @Test
    @DisplayName("toString 应该返回有意义的描述")
    void shouldReturnMeaningfulToString() {
        Lexicon dictionary = Dictionary.english(Set.of("test"));
        SpellChecker checker = new SpellChecker(dictionary);
        
        String description = checker.toString();
        
        assertTrue(description.contains("SpellChecker"));
        assertTrue(description.contains("en"));
    }
}
