package com.iokays.core.mongodb;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

@Slf4j
class MongoSURTest extends AbstractMongoTest {

    @Test
    @DisplayName("base 测试")
    void testBase() throws InterruptedException {
        final String johnDoe = "John Doe";
        final Person person = template.insert(new Person(johnDoe, 42));
        final long count = template.query(Person.class).matching(query(where("name").is(johnDoe))).stream().count();
        log.info("count: {}", count);
        Person person2 = template.findById(person.getId(), Person.class);
        log.info("person2: {}", person2);
        template.updateFirst(query(where("name").is(johnDoe)), update("age", 43), Person.class);
    }

    @Test
    @DisplayName("更新 测试")
    void testUpdate() {
        template.remove(Person.class);

        final var name = "Iokays";
        template.insert(new Person(name, 18));

        Optional<Person> modify = template.update(Person.class).matching(query(where("name").is(name)))
                .apply(new Update().inc("age", 1))
                .findAndModify();

        log.info("modify: {}", modify);

        Optional<Person> modifyNew = template.update(Person.class).matching(query(where("name").is(name)))
                .apply(new Update().inc("age", 1))
                .withOptions(FindAndModifyOptions.options().returnNew(true))
                .findAndModify();

        log.info("modifyNew: {}", modifyNew);


    }

}
