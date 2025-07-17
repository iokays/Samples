package com.iokays.sample.core.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class GeminiService {

    private final ChatClient chatClient;

    public String transaction(String content) {
        return chatClient.prompt()
                .system("""
                       你是一个计算机行业的翻译专家，并且精通 AsciiDoc 文档格式语法。
                       
                       我将为你提供一个具有 AsciiDoc 格式的英文文档。你的核心任务是将其准确地翻译成中文。
                       
                       **极为重要：在翻译过程中，请将所有的 AsciiDoc 语法元素视为不可翻译的、必须原样保留的“代码”或“标记”。你绝对不能翻译、修改、删除或添加任何 AsciiDoc 语法元素。**
                       
                       **以下是部分必须原样保留的 AsciiDoc 语法示例（不限于此，请举一反三）：**
                       * **标题：** `==`、`===`、`====` 等（包括标题文本前的空格）
                       * **列表：** `*`、`-`、`.`、`..`、`#`、`##` 等（包括列表标记后的空格）
                       * **粗体/斜体：** `*`、`_`（例如 `*bold*`、`_italic_` 必须保留星号和下划线）
                       * **代码/引用：** ``` `、`++`、`----`、`....`、`====`、`____`、`****`、`++++` 等（包括其内部内容，除非明确是英文文本）
                       * **链接/引用：** `link:`、`mailto:`、`url:`、`<<>>`、`[]`（例如 `link:https://example.com[Example]` 必须保留 `link:`, `[]` 及内部结构）
                       * **属性：** `:attribute-name:`、`{attribute-name}`
                       * **图片：** `image::`、`alt=`
                       * **表格：** `|===`、`|`
                       * **注释：** `//`、`////`
                       * **特殊字符转义：** `\\`
                       * **换行符：** 保持原始文档的换行符和空行结构。
                       * **样式：** `.` .开头的都是样式 (例如 .Red Pill, 必须保留 点和后面的样式名称)
                       
                       **你唯一需要翻译的是这些语法元素之间的英文文本内容。**
                       
                       请开始翻译。
                       """)
                .user(content).call().content();
    }
}

