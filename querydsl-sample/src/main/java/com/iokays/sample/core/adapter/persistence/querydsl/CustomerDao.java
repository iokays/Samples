package com.iokays.sample.core.adapter.persistence.querydsl;

import com.iokays.sample.core.adapter.persistence.querydsl.model.Customer;
import com.iokays.sample.core.adapter.persistence.querydsl.model.QCustomer;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.sql.SQLQueryFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class CustomerDao {

    private final SQLQueryFactory sqlQueryFactory;

    // 全字段的查询
    public List<Customer> findAll() {
        final QCustomer qCustomer = QCustomer.qCustomer;

        return sqlQueryFactory
                .select(qCustomer)
                .from(qCustomer)
                .orderBy(qCustomer.id.desc())
                .fetch();
    }

    // 部分字段的查询
    public List<Customer> findAllEntitiesOnlyReturnName() {
        final QCustomer qCustomer = QCustomer.qCustomer;

        return sqlQueryFactory
                .select(Projections.bean(
                        Customer.class,
                        qCustomer.name)
                ).from(qCustomer)
                .orderBy(qCustomer.id.desc())
                .fetch();
    }

    // 单个字段查询
    public List<String> findNames() {
        final QCustomer qCustomer = QCustomer.qCustomer;

        final List<String> names = sqlQueryFactory
                .select(qCustomer.name)
                .from(qCustomer)
                .orderBy(qCustomer.id.desc()).fetch();

        log.debug("names: {}", names);
        return names;
    }

    //自定义函数查询
    public Long findCount() {
        final QCustomer qCustomer = QCustomer.qCustomer;

        // final var count = ExpressionUtils.count(qCustomer); //方法一, SQL标准函数, 只需要添加字段
        final var count = Expressions.numberTemplate(Long.class, "count(*)"); //方法二, 直接自定义函数,

        return sqlQueryFactory
                .select(count)
                .from(qCustomer)
                .fetchFirst();
    }

}
