package com.iokays.rule.order;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngineParameters;
import org.jeasy.rules.core.DefaultRulesEngine;

public class MainApp {
    public static void main(String[] args) {
        // 创建订单
        Order order = new Order("John", 120.0);

        // 创建规则集
        Rules rules = new Rules();
        rules.register(new DiscountRule());

        // 执行规则
        RulesEngineParameters parameters = new RulesEngineParameters().skipOnFirstAppliedRule(true);
        DefaultRulesEngine rulesEngine = new DefaultRulesEngine(parameters);
        rulesEngine.registerRuleListener(new CustomRuleListener());

        // 执行规则
        final Facts facts = new Facts();
        facts.put("order", order);
        rulesEngine.fire(rules, facts);

        System.out.println("Customer: " + order.getCustomer() + ", Total after discount: " + order.getTotal());
    }
}
