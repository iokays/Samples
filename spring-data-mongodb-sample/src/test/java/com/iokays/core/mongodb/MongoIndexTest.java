package com.iokays.core.mongodb;

import com.iokays.core.domain.person.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.index.Index;

@Slf4j
class MongoIndexTest extends AbstractMongoTest {

    @Test
    @DisplayName("测试 创建, 删除索引")
    void testIndex() {
        final String indexName = "name_1";

        // 创建索引
        final var name = template.indexOps(Person.class).ensureIndex(new Index().named(indexName).on("name", Sort.Direction.ASC));
        log.info("indexName: {}", name);

        // 查询索引
        final var indexInfo = template.indexOps(Person.class).getIndexInfo();
        log.info("indexInfo: {}", indexInfo);

        //删除索引
        template.indexOps(Person.class).dropIndex(indexName);

    }
}
