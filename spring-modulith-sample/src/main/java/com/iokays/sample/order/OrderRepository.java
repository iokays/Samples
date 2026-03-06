// Path: Samples/spring-modulith-sample/src/main/java/com/iokays/sample/order/OrderRepository.java
package com.iokays.sample.order;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

interface OrderRepository extends CrudRepository<Order, UUID> {}
