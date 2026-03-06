// Path: Samples/spring-modulith-sample/src/main/java/com/iokays/sample/order/OrderCompleted.java
package com.iokays.sample.order;

import java.util.UUID;

/**
 * 表示订单已完成的事件。
 *
 * @param orderId 订单ID
 */
public record OrderCompleted(UUID orderId) {}
