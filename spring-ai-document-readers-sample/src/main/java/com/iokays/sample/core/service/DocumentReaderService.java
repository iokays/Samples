package com.iokays.sample.core.service;

import com.iokays.sample.config.DocumentResourceConfiguration;
import lombok.AllArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.document.DocumentReader;
import org.springframework.ai.reader.ExtractedTextFormatter;
import org.springframework.ai.reader.JsonReader;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.reader.jsoup.JsoupDocumentReader;
import org.springframework.ai.reader.jsoup.config.JsoupDocumentReaderConfig;
import org.springframework.ai.reader.markdown.MarkdownDocumentReader;
import org.springframework.ai.reader.markdown.config.MarkdownDocumentReaderConfig;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DocumentReaderService {

    private final DocumentResourceConfiguration resources;

    List<Document> loadJsonAsDocuments() {
        final JsonReader jsonReader = new JsonReader(this.resources.getJson(), "个人信息");
        return jsonReader.get();
    }

    List<Document> loadTextAsDocuments() {
        final DocumentReader reader = new TextReader(this.resources.getText());
        return reader.get();
    }

    List<Document> loadHtml() {
        JsoupDocumentReaderConfig config = JsoupDocumentReaderConfig.builder()
                .selector("article p") // Extract paragraphs within <article> tags
                .charset("ISO-8859-1")  // Use ISO-8859-1 encoding
                .includeLinkUrls(true) // Include link URLs in metadata
                .metadataTags(List.of("author", "date")) // Extract author and date meta tags
                .additionalMetadata("source", "my-page.html") // Add custom metadata
                .build();

        JsoupDocumentReader reader = new JsoupDocumentReader(this.resources.getHtml(), config);
        return reader.get();
    }

    List<Document> loadMarkdown() {
        MarkdownDocumentReaderConfig config = MarkdownDocumentReaderConfig.builder()
                .withHorizontalRuleCreateDocument(true)
                .withIncludeCodeBlock(false)
                .withIncludeBlockquote(false)
                .withAdditionalMetadata("filename", "markdown-sample.md")
                .build();

        MarkdownDocumentReader reader = new MarkdownDocumentReader(this.resources.getMarkdown(), config);
        return reader.get();
    }

    public List<Document> getDocsFromPdf() {
        PagePdfDocumentReader pdfReader = new PagePdfDocumentReader(this.resources.getPdf(),
                PdfDocumentReaderConfig.builder()
                        .withPageTopMargin(0)
                        .withPageExtractedTextFormatter(ExtractedTextFormatter.builder()
                                .withNumberOfTopTextLinesToDelete(0)
                                .build())
                        .withPagesPerDocument(1)
                        .build());

        return pdfReader.read();
    }

    public List<Document> loadExcel() {
        TikaDocumentReader tikaDocumentReader = new TikaDocumentReader(this.resources.getExcel());
        return tikaDocumentReader.read();
    }


}
