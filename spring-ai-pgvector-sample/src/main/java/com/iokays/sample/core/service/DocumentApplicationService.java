package com.iokays.sample.core.service;

import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.pgvector.PgVectorStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class DocumentApplicationService {

    @Resource
    private final PgVectorStore vectorStore;

    public void save(final String content) {
        Validate.notEmpty(content, "内容不能为空");
        final var id = UUID.randomUUID().toString();
        final var document = Document.builder()
                .id(id)
                .text(content)
                .metadata(Map.of("docCode", id))
                .build();
        vectorStore.add(List.of(document));
    }

    public List<String> topK(final String query, int topK) {
        Validate.notEmpty(query);
        Validate.isTrue(topK >= 0);

        final SearchRequest searchRequest = SearchRequest.builder()
                .query(query)
                .topK(topK)
                .build();

        return CollectionUtils.emptyIfNull(vectorStore.similaritySearch(searchRequest))
                .stream().map(Document::getText).toList();
    }

    public void deleteAll() {
        // 删除所有文档，匹配所有条件
        vectorStore.delete("docCode != ''");
    }

}
