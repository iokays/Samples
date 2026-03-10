package com.iokays.objectreuse.injection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * SpellChecker 测试类。
 */
@DisplayName("拼写检查器测试")
class SpellCheckerTest {
    
    /**
     * 简单的词典实现（用于测试）。
     */
    static class SimpleLexicon implements Lexicon {
        private final List<String> words;
        
        SimpleLexicon(String... words) {
            this.words = Arrays.asList(words);
        }
        
        @Override
        public boolean isValid(String word) {
            return words.contains(word.toLowerCase());
        }
        
        @Override
        public List<String> suggestions(String typo) {
            // 简单实现：返回相似的单词
            return words.stream()
                .filter(w -> w.startsWith(typo.substring(0, 1)))
                .limit(3)
                .toList();
        }
    }
    
    @Test
    @DisplayName("依赖注入：注入词典实现")
    void testDependencyInjection() {
        Lexicon lexicon = new SimpleLexicon("hello", "world", "java");
        SpellChecker checker = new SpellChecker(lexicon);
        
        assertTrue(checker.isValid("hello"));
        assertTrue(checker.isValid("WORLD")); // 不区分大小写
        assertFalse(checker.isValid("python"));
    }
    
    @Test
    @DisplayName("词典重用：多个 SpellChecker 共享同一个词典")
    void testDictionaryReuse() {
        Lexicon sharedLexicon = new SimpleLexicon("test", "code", "java");
        
        // 多个 SpellChecker 共享同一个词典实例
        SpellChecker checker1 = new SpellChecker(sharedLexicon);
        SpellChecker checker2 = new SpellChecker(sharedLexicon);
        
        // 都能访问相同的词典
        assertTrue(checker1.isValid("test"));
        assertTrue(checker2.isValid("test"));
    }
    
    @Test
    @DisplayName("空指针检查：词典不能为 null")
    void testNullDictionary() {
        assertThrows(NullPointerException.class, () -> {
            new SpellChecker(null);
        });
    }
    
    @Test
    @DisplayName("空指针检查：单词不能为 null")
    void testNullWord() {
        Lexicon lexicon = new SimpleLexicon("test");
        SpellChecker checker = new SpellChecker(lexicon);
        
        assertThrows(NullPointerException.class, () -> {
            checker.isValid(null);
        });
    }
    
    @Test
    @DisplayName("获取拼写建议")
    void testSuggestions() {
        Lexicon lexicon = new SimpleLexicon("hello", "help", "helicopter", "world");
        SpellChecker checker = new SpellChecker(lexicon);
        
        List<String> suggestions = checker.suggestions("hel");
        assertFalse(suggestions.isEmpty());
        assertTrue(suggestions.contains("hello"));
    }
}
