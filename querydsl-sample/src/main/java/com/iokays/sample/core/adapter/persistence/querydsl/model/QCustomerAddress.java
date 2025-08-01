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
 * QCustomerAddress is a Querydsl query type for CustomerAddress
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QCustomerAddress extends com.querydsl.sql.RelationalPathBase<CustomerAddress> {

    public static final QCustomerAddress qCustomerAddress = new QCustomerAddress("q_customer_address");
    private static final long serialVersionUID = -952611967;
    public final StringPath city = createString("city");

    public final NumberPath<Integer> customerId = createNumber("customerId", Integer.class);

    public final StringPath detailedAddress = createString("detailedAddress");

    public final StringPath district = createString("district");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath province = createString("province");

    public final com.querydsl.sql.PrimaryKey<CustomerAddress> qCustomerAddressPkey = createPrimaryKey(id);

    public final com.querydsl.sql.ForeignKey<Customer> customerFk = createForeignKey(customerId, "id");

    public QCustomerAddress(String variable) {
        super(CustomerAddress.class, forVariable(variable), "public", "q_customer_address");
        addMetadata();
    }

    public QCustomerAddress(String variable, String schema, String table) {
        super(CustomerAddress.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QCustomerAddress(String variable, String schema) {
        super(CustomerAddress.class, forVariable(variable), schema, "q_customer_address");
        addMetadata();
    }

    public QCustomerAddress(Path<? extends CustomerAddress> path) {
        super(path.getType(), path.getMetadata(), "public", "q_customer_address");
        addMetadata();
    }

    public QCustomerAddress(PathMetadata metadata) {
        super(CustomerAddress.class, metadata, "public", "q_customer_address");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(city, ColumnMetadata.named("city").withIndex(4).ofType(Types.VARCHAR).withSize(50).notNull());
        addMetadata(customerId, ColumnMetadata.named("customer_id").withIndex(2).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(detailedAddress, ColumnMetadata.named("detailed_address").withIndex(6).ofType(Types.VARCHAR).withSize(2147483647).notNull());
        addMetadata(district, ColumnMetadata.named("district").withIndex(5).ofType(Types.VARCHAR).withSize(50));
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(province, ColumnMetadata.named("province").withIndex(3).ofType(Types.VARCHAR).withSize(50).notNull());
    }

}

