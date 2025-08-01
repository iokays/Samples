package com.iokays.sample.core.adapter.persistence.querydsl.model;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.sql.ColumnMetadata;

import javax.annotation.processing.Generated;
import java.sql.Types;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QCustomer is a Querydsl query type for Customer
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QCustomer extends com.querydsl.sql.RelationalPathBase<Customer> {

    public static final QCustomer qCustomer = new QCustomer("q_customer");
    private static final long serialVersionUID = 1696956659;
    public final StringPath customerCode = createString("customerCode");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final com.querydsl.sql.PrimaryKey<Customer> qCustomerPkey = createPrimaryKey(id);

    public final com.querydsl.sql.ForeignKey<CustomerAddress> _customerFk = createInvForeignKey(id, "customer_id");

    public QCustomer(String variable) {
        super(Customer.class, forVariable(variable), "public", "q_customer");
        addMetadata();
    }

    public QCustomer(String variable, String schema, String table) {
        super(Customer.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QCustomer(String variable, String schema) {
        super(Customer.class, forVariable(variable), schema, "q_customer");
        addMetadata();
    }

    public QCustomer(Path<? extends Customer> path) {
        super(path.getType(), path.getMetadata(), "public", "q_customer");
        addMetadata();
    }

    public QCustomer(PathMetadata metadata) {
        super(Customer.class, metadata, "public", "q_customer");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(customerCode, ColumnMetadata.named("customer_code").withIndex(2).ofType(Types.VARCHAR).withSize(50).notNull());
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(name, ColumnMetadata.named("name").withIndex(3).ofType(Types.VARCHAR).withSize(100).notNull());
    }

}

