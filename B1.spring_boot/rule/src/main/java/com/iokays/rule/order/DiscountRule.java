package com.iokays.rule.order;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

@Rule(name = "Discount Rule", description = "Apply a discount if the order total is greater than 100")
public class DiscountRule {

    @Condition
    public boolean shouldApplyDiscount(@Fact("order") Order order) {
        return order.getTotal() > 100;
    }

    @Action
    public void applyDiscount(@Fact("order") Order order) {
        order.setTotal(order.getTotal() * 0.9); // Apply a 10% discount
    }
}
