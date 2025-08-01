package com.iokays.sample.core.adapter.persistence.querydsl;

import com.iokays.sample.core.adapter.persistence.querydsl.model.Customer;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional //查询资源库的事务管理
class CustomerDaoTest {

    @Resource
    private CustomerDao customerDao;

    @Test
    void testFindAll() {
        List<Customer> customers = customerDao.findAll();
        Assertions.assertEquals(5, CollectionUtils.size(customers));
        Assertions.assertNotNull(customers.getFirst().getId());
        Assertions.assertNotNull(customers.getFirst().getName());
    }

    @Test
    void testFindAllEntitiesOnlyReturnName() {
        List<Customer> customers = customerDao.findAllEntitiesOnlyReturnName();
        Assertions.assertEquals(5, CollectionUtils.size(customers));
        Assertions.assertNull(customers.getFirst().getId());
        Assertions.assertNotNull(customers.getFirst().getName());
    }


    @Test
    void testFindNames() {
        List<String> names = customerDao.findNames();
        Assertions.assertEquals(5, CollectionUtils.size(names));
        Assertions.assertTrue(names.stream().allMatch(StringUtils::isNotBlank));
    }

    @Test
    void testCount() {
        final var count = customerDao.findCount();
        Assertions.assertNotNull(count);
        Assertions.assertEquals(5L, count);
    }

}