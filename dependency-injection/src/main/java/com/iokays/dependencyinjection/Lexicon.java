package com.iokays.dependencyinjection;

/**
 * 词典接口 - 拼写检查器的底层资源依赖
 * 
 * <p>词典是拼写检查器的核心依赖项。通过将词典抽象为接口，
 * 我们可以支持不同语言的词典、特殊词汇表，以及测试用的模拟词典。
 * 
 * <p>这种设计体现了"依赖注入优于硬连接资源"的核心思想：
 * 类不应该直接创建或管理其依赖的资源，而应该通过构造器接收依赖。
 */
public interface Lexicon {
    
    /**
     * 检查单词是否存在于词典中
     * 
     * @param word 待检查的单词
     * @return 如果词典包含该单词，返回 true
     */
    boolean contains(String word);
    
    /**
     * 获取词典支持的语言
     * 
     * @return 语言标识符（如 "en", "zh"）
     */
    String getLanguage();
}
