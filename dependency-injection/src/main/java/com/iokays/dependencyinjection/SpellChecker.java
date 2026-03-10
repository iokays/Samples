package com.iokays.dependencyinjection;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 正确实现：使用依赖注入的拼写检查器
 * 
 * <p>这种实现方式的优势：
 * <ul>
 *   <li><b>灵活性</b>：可以支持任何语言的词典，只需传入不同的 Lexicon 实现</li>
 *   <li><b>可测试性</b>：可以注入模拟词典进行单元测试</li>
 *   <li><b>可重用性</b>：同一个 SpellChecker 类可用于不同场景</li>
 *   <li><b>线程安全</b>：词典是不可变的，多个客户端可以共享实例</li>
 * </ul>
 * 
 * <p>这就是依赖注入的核心思想：在创建新实例时将资源传递到构造器中。
 * 字典是拼写检查器的一个依赖项，当它创建时被注入到拼写检查器中。
 */
public class SpellChecker {
    
    private final Lexicon dictionary;
    
    /**
     * 构造器注入 - 接收词典依赖
     * 
     * <p>这是依赖注入的最简单形式：构造器注入。
     * 客户端在创建 SpellChecker 时，传入所需的词典。
     * 
     * @param dictionary 词典依赖项（不能为 null）
     * @throws NullPointerException 如果 dictionary 为 null
     */
    public SpellChecker(Lexicon dictionary) {
        this.dictionary = Objects.requireNonNull(dictionary, "dictionary must not be null");
    }
    
    /**
     * 检查单词拼写是否正确
     * 
     * @param word 待检查的单词
     * @return 如果单词在词典中，返回 true
     */
    public boolean isValid(String word) {
        return dictionary.contains(word);
    }
    
    /**
     * 获取拼写建议（简化实现）
     * 
     * <p>在实际应用中，这里会实现更复杂的拼写纠正算法，
     * 如编辑距离、语音匹配等。
     * 
     * @param typo 可能拼写错误的单词
     * @return 建议的正确单词列表
     */
    public List<String> suggestions(String typo) {
        // 简化实现：实际会根据词典生成拼写建议
        return new ArrayList<>();
    }
    
    /**
     * 获取当前使用的词典语言
     * 
     * @return 词典语言标识
     */
    public String getDictionaryLanguage() {
        return dictionary.getLanguage();
    }
    
    @Override
    public String toString() {
        return "SpellChecker{language='" + dictionary.getLanguage() + "'}";
    }
}
