// Path: Samples/spring-modulith-sample/src/main/java/com/iokays/sample/inventory/InventoryManagement.java
package com.iokays.sample.inventory;

import com.iokays.sample.order.OrderCompleted;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;

@Service
public class InventoryManagement {

    private final InventoryRepository inventory;

    public InventoryManagement(InventoryRepository inventory) {
        this.inventory = inventory;
    }

    /**
     * 监听订单完成事件，并更新库存。
     *
     * @param event 订单完成事件
     */
    @ApplicationModuleListener
    void on(OrderCompleted event) {
        // 预计输出:
        // 收到订单完成事件，为订单 [orderId] 更新库存。
        System.out.println("收到订单完成事件，为订单 " + event.orderId() + " 更新库存。");
        // 在实际应用中，这里会有更复杂的逻辑，例如查找与订单相关的商品并减少库存。
        // 为简化示例，我们只打印一条消息。
    }
}
