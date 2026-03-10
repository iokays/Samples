package com.iokays.dependencyinjection;

import java.util.List;
import java.util.Set;

/**
 * 错误示例：使用静态工具类实现拼写检查器
 * 
 * <p>这种实现方式存在严重缺陷：
 * <ul>
 *   <li><b>不灵活</b>：假设只有一本字典可用，无法支持多语言或特殊词汇表</li>
 *   <li><b>不可测试</b>：难以用模拟词典进行单元测试</li>
 *   <li><b>硬编码依赖</b>：字典在类加载时就固定了，无法更改</li>
 * </ul>
 * 
 * <p>这种设计违反了"依赖注入优于硬连接资源"的原则。
 * 
 * @deprecated 不要使用静态工具类来管理依赖资源的类
 */
@Deprecated
public class SpellCheckerStatic {
    
    // 硬编码的词典 - 假设只有一本字典可用
    private static final Lexicon dictionary = new Dictionary("en", Set.of("hello", "world"));
    
    // 不可实例化
    private SpellCheckerStatic() {}
    
    /**
     * 检查单词拼写是否正确
     * 
     * @param word 待检查的单词
     * @return 如果单词在词典中，返回 true
     */
    public static boolean isValid(String word) {
        return dictionary.contains(word);
    }
    
    /**
     * 获取拼写建议（简化实现）
     * 
     * @param typo 可能拼写错误的单词
     * @return 建议的正确单词列表
     */
    public static List<String> suggestions(String typo) {
        // 实际实现会更复杂，这里仅作演示
        return List.of();
    }
    
    /**
     * 获取当前使用的词典语言
     * 
     * @return 词典语言标识
     */
    public static String getDictionaryLanguage() {
        return dictionary.getLanguage();
    }
}
