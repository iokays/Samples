package com.iokays.dependencyinjection;

import java.util.HashSet;
import java.util.Set;

/**
 * 词典实现 - 英文词典
 * 
 * <p>这是一个简单的词典实现，使用 HashSet 存储单词。
 * 在实际应用中，词典可能从数据库、文件或远程服务加载。
 * 
 * <p>通过接口抽象，我们可以轻松创建不同语言的词典实现，
 * 或者创建用于测试的模拟词典。
 */
public class Dictionary implements Lexicon {
    
    private final String language;
    private final Set<String> words;
    
    /**
     * 构造器 - 接收语言标识和单词集合
     * 
     * <p>所有单词在存储时会被转换为小写，以支持大小写不敏感的查找。
     * 
     * @param language 语言标识符
     * @param words 单词集合
     */
    public Dictionary(String language, Set<String> words) {
        this.language = language;
        // 将所有单词转换为小写存储，支持大小写不敏感查找
        this.words = new HashSet<>();
        for (String word : words) {
            this.words.add(word.toLowerCase());
        }
    }
    
    /**
     * 创建英文词典的便捷方法
     * 
     * @param words 单词集合
     * @return 英文词典实例
     */
    public static Dictionary english(Set<String> words) {
        return new Dictionary("en", words);
    }
    
    /**
     * 创建中文词典的便捷方法
     * 
     * @param words 单词集合
     * @return 中文词典实例
     */
    public static Dictionary chinese(Set<String> words) {
        return new Dictionary("zh", words);
    }
    
    @Override
    public boolean contains(String word) {
        return words.contains(word.toLowerCase());
    }
    
    @Override
    public String getLanguage() {
        return language;
    }
}
