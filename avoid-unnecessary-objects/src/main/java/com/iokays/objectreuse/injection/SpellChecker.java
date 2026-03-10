package com.iokays.objectreuse.injection;

import java.util.List;
import java.util.Objects;

/**
 * 演示依赖注入实现对象重用。
 * 
 * <p>核心原则：通过构造器注入依赖，而非在内部创建依赖对象。
 * 
 * <p>优势：
 * <ul>
 *   <li>灵活性：可以注入不同的实现（如不同语言的词典）</li>
 *   <li>可测试性：可以注入 Mock 对象进行单元测试</li>
 *   <li>资源重用：多个 SpellChecker 可以共享同一个 Lexicon 实例</li>
 * </ul>
 */
public class SpellChecker {
    
    /**
     * 词典依赖，通过构造器注入。
     * 使用 final 确保不可变，保证线程安全。
     */
    private final Lexicon dictionary;
    
    /**
     * 构造器注入：明确依赖关系，支持依赖重用。
     * 
     * @param dictionary 词典实现（不能为 null）
     * @throws NullPointerException 如果 dictionary 为 null
     */
    public SpellChecker(Lexicon dictionary) {
        this.dictionary = Objects.requireNonNull(dictionary, "词典不能为 null");
    }
    
    /**
     * 检查单词拼写是否正确。
     * 
     * @param word 待检查的单词
     * @return 拼写是否正确
     */
    public boolean isValid(String word) {
        Objects.requireNonNull(word, "单词不能为 null");
        return dictionary.isValid(word);
    }
    
    /**
     * 获取拼写建议列表。
     * 
     * @param typo 拼写错误的单词
     * @return 建议的正确单词列表
     */
    public List<String> suggestions(String typo) {
        Objects.requireNonNull(typo, "单词不能为 null");
        return dictionary.suggestions(typo);
    }
}

/**
 * 词典接口，定义拼写检查所需的操作。
 */
interface Lexicon {
    
    /**
     * 检查单词是否有效。
     * 
     * @param word 待检查的单词
     * @return 单词是否在词典中
     */
    boolean isValid(String word);
    
    /**
     * 获取拼写建议。
     * 
     * @param typo 拼写错误的单词
     * @return 建议的正确单词列表
     */
    List<String> suggestions(String typo);
}
