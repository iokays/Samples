package com.iokays.dependencyinjection;

import java.util.List;
import java.util.Set;

/**
 * 错误示例：使用单例模式实现拼写检查器
 * 
 * <p>这种实现方式同样存在缺陷：
 * <ul>
 *   <li><b>不灵活</b>：虽然 dictionary 是 final 而非 static，
 *       但单例模式限制了只能有一个实例，无法支持多语言场景</li>
 *   <li><b>不可测试</b>：难以用模拟词典进行单元测试</li>
 *   <li><b>全局状态</b>：单例是全局状态的一种形式，增加了系统的复杂性</li>
 * </ul>
 * 
 * <p>这种设计违反了"依赖注入优于硬连接资源"的原则。
 * 
 * @deprecated 不要使用单例来管理依赖资源的类
 */
@Deprecated
public class SpellCheckerSingleton {
    
    // 硬编码的词典 - 在构造时固定
    private final Lexicon dictionary;
    
    // 单例实例
    public static final SpellCheckerSingleton INSTANCE = new SpellCheckerSingleton();
    
    // 私有构造器
    private SpellCheckerSingleton() {
        // 词典硬编码在构造器中
        this.dictionary = new Dictionary("en", Set.of("hello", "world"));
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
     * @param typo 可能拼写错误的单词
     * @return 建议的正确单词列表
     */
    public List<String> suggestions(String typo) {
        // 实际实现会更复杂，这里仅作演示
        return List.of();
    }
    
    /**
     * 获取当前使用的词典语言
     * 
     * @return 词典语言标识
     */
    public String getDictionaryLanguage() {
        return dictionary.getLanguage();
    }
}
