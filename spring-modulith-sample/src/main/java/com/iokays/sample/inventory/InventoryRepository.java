// Path: Samples/spring-modulith-sample/src/main/java/com/iokays/sample/inventory/InventoryRepository.java
package com.iokays.sample.inventory;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

interface InventoryRepository extends CrudRepository<Inventory, UUID> {}
