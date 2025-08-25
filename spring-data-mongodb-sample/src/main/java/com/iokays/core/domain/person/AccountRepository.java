package com.iokays.core.domain.person;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 不建议 在这里继承 MongoRepository，原因有2： 详细结构可以查看 common-domain-with-spring-data-jpa 模块
 * 1. 因为 MongoRepository 还提供了 Reactor模式，应该在这里定义方法，放在聚合根中，然后在基础实施层中，再继承。
 * 2. 这里暴露的方法太多。
 */
public interface AccountRepository extends MongoRepository<Account, String> {
}
