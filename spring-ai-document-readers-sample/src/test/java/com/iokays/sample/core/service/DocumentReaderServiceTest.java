package com.iokays.sample.core.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.apache.commons.lang3.Strings.CS;

@Slf4j
@SpringBootTest
class DocumentReaderServiceTest {

    @Resource
    private DocumentReaderService documentReaderService;

    @Test
    void testJson() {
        final List<Document> documents = documentReaderService.loadJsonAsDocuments();
        Assertions.assertEquals(1, documents.size());
        final Document json = documents.getFirst();
        Assertions.assertEquals("个人信息: {姓名=张伟, 年龄=32, 性别=男, 联系方式={电子邮箱=zhangwei@example.com, 手机号码=+86-138-0011-2233, 地址={街道=浦东新区张江路123号, 城市=上海市, 省份=上海市, 邮政编码=201210, 国家=中国}}}\n", json.getText());
    }

    @Test
    void testText() {
        final List<Document> documents = documentReaderService.loadTextAsDocuments();
        Assertions.assertEquals(1, documents.size());
        final var text = documents.getFirst();
        log.info("text: {}", text.getText());
        Assertions.assertTrue(CS.contains(text.getText(), "Youth"));
    }

    @Test
    void testHtml() {
        final List<Document> documents = documentReaderService.loadHtml();
        Assertions.assertEquals(1, documents.size());
        final var html = documents.getFirst();
        log.info("html: {}", html.getText());
        Assertions.assertTrue(CS.contains(html.getText(), "This is the main content of my web page."));
    }

    @Test
    void testMarkdown() {
        //每个段落都会被分割成一个文档
        final List<Document> documents = documentReaderService.loadMarkdown();
        Assertions.assertEquals(5, documents.size());
        final var markdown = documents.getFirst();
        log.info("markdown: {}", markdown.getText());
        Assertions.assertTrue(CS.contains(markdown.getText(), "This is a Java sample application:"));
    }

    @Test
    void testPDF() {
        //每个段落都会被分割成一个文档
        final List<Document> documents = documentReaderService.getDocsFromPdf();
        Assertions.assertEquals(2, documents.size());
        final var pdf = documents.getFirst();
        log.info("pdf: {}", pdf.getText());
        Assertions.assertTrue(CS.contains(pdf.getText(), "YOUTH"));
    }

    @Test
    void testExcel() {
        //每个段落都会被分割成一个文档
        final List<Document> documents = documentReaderService.loadExcel();
        Assertions.assertEquals(1, documents.size());
        final var excel = documents.getFirst();
        log.info("excel: {}", excel.getText());
        Assertions.assertTrue(CS.contains(excel.getText(), "王羲之"));
    }

}