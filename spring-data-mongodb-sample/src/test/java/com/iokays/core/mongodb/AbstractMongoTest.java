package com.iokays.core.mongodb;

import com.iokays.core.domain.person.Person;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

@Slf4j
@SpringBootTest
abstract class AbstractMongoTest {

    @Resource
    protected MongoTemplate template;

    @BeforeEach
    public void before() {
        log.info("before");
        //del all
        template.remove(Person.class).matching(new Query()).all();
    }

}
