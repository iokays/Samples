package com.iokays.sample.core.domain.sample;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@SpringBootTest
class MyEntityRepositoryTest {

    @Resource
    private MyEntityRepository repository;

    @Test
    void test() {
        final MyEntity entity = new MyEntity(); //新建一个实体, 请勿给version赋值,触发为更新的操作.

        //表字段: 起到字段分组作用, 不会生成新的表, 也不会有外键关联.
        final MyEmbeddedEntity embeddedEntity = new MyEmbeddedEntity();
        embeddedEntity.setAddress("莲花山,深圳,广东省,中国");
        entity.setEmbeddedEntity(embeddedEntity);

        //关联表: 处理一对多的关系
        final MySubEntity andy = new MySubEntity();
        andy.setName("Andy");
        final MySubEntity tom = new MySubEntity();
        tom.setName("Tom");
        entity.setNames(List.of(tom, andy, andy));
        repository.save(entity); //新实体
        Assertions.assertNotNull(entity.getId()); //新增后, id有值
        Assertions.assertEquals(0, entity.getVersion()); //只在聚合根上使用版本控制, 插入后, version = 0

        MyEntity result = repository.findById(entity.getId()).orElseThrow();
        log.info("result: {}", result);
        Assertions.assertEquals("莲花山,深圳,广东省,中国", result.getEmbeddedEntity().getAddress());
        Assertions.assertEquals(0, result.getVersion());
        Assertions.assertEquals(3, result.getNames().size());
        Assertions.assertEquals("Tom", result.getNames().getFirst().getName()); //插入的顺序
        Assertions.assertEquals("Andy", result.getNames().getLast().getName());

        embeddedEntity.setAddress("小梧桐,深圳,广东省,中国");
        entity.setEmbeddedEntity(embeddedEntity);
        entity.setNames(List.of(tom));
        repository.save(entity);
        result = repository.findById(entity.getId()).orElseThrow();
        log.info("result: {}", result);
        Assertions.assertEquals("小梧桐,深圳,广东省,中国", result.getEmbeddedEntity().getAddress());
        Assertions.assertEquals(1, result.getVersion());
        Assertions.assertEquals(1, result.getNames().size());
        Assertions.assertEquals("Tom", result.getNames().getFirst().getName());

        repository.delete(entity);
        Assertions.assertThrows(NoSuchElementException.class, () -> repository.findById(entity.getId()).orElseThrow());

    }

}