package com.iokays.sample.core.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class JsonDocumentServiceTest {

    private static JsonDocumentService service;

    @BeforeAll
    static void beforeAll() {
        service = new JsonDocumentService(HTML);
    }

    @Test
    void testTitle() {
        //测试标题
        Assertions.assertEquals("Jsoup XPath 测试页面", service.title());
    }

    @Test
    void testMeta() {
        //测试 meta 标签
        Assertions.assertEquals("width=device-width, initial-scale=1.0", service.metaVal("viewport", "content"));
    }

    @Test
    void testAttr() {
        //测试属性值
        Assertions.assertEquals("欢迎来到测试页面", service.attrVal("//h1", null).findFirst().orElse(null));
        Assertions.assertEquals("这是一个用于测试Jsoup XPath功能的简单HTML页面", service.attrVal("//p[@class='intro']", null).findFirst().orElse(null));
        Assertions.assertEquals("首页", service.attrVal("//ul[@id='nav']/li/a", null).findFirst().orElse(null));
        Assertions.assertEquals("/home", service.attrVal("//ul[@id='nav']/li/a", "href").findFirst().orElse(null));
    }

    public static final String HTML = """
            <!DOCTYPE html>
            <html lang="zh-CN">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Jsoup XPath 测试页面</title>
            </head>
            <body>
                <div id="header">
                    <h1>欢迎来到测试页面</h1>
                    <p class="intro">这是一个用于测试Jsoup XPath功能的简单HTML页面</p>
                </div>
                
                <div id="content">
                    <section class="articles">
                        <article>
                            <h2>文章标题1</h2>
                            <p>这是第一篇文章的内容。</p>
                            <a href="/article1">阅读更多</a>
                        </article>
                        <article>
                            <h2>文章标题2</h2>
                            <p>这是第二篇文章的内容。</p>
                            <a href="/article2">阅读更多</a>
                        </article>
                    </section>
                    
                    <aside class="sidebar">
                        <ul id="nav">
                            <li><a href="/home">首页</a></li>
                            <li><a href="/about">关于我们</a></li>
                            <li><a href="/contact">联系我们</a></li>
                        </ul>
                        
                        <div class="widget">
                            <h3>搜索</h3>
                            <form action="/search" method="get">
                                <input type="text" name="q" placeholder="输入关键词">
                                <button type="submit">搜索</button>
                            </form>
                        </div>
                    </aside>
                </div>
                
                <div id="footer">
                    <p>© 2025 IOKAYS. 保留所有权利.</p>
                    <p class="disclaimer">免责声明：本页面仅用于测试目的。</p>
                </div>
                
                <!-- 隐藏的注释 -->
                <script>
                    // 一些JavaScript代码
                    console.log("页面加载完成");
                </script>
            </body>
            </html>
            
            """;
}

