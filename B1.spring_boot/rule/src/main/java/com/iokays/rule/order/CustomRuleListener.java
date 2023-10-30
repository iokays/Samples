package com.iokays.rule.order;

import org.jeasy.rules.api.*;

public class CustomRuleListener implements RuleListener {
    @Override
    public boolean beforeEvaluate(Rule rule, Facts facts) {
        System.out.println("Before evaluating rule: " + rule.getName());
        return true;
    }

    @Override
    public void afterEvaluate(Rule rule, Facts facts, boolean evaluationResult) {
        System.out.println("After evaluating rule: " + rule.getName() + ", Result: " + evaluationResult);
    }

    @Override
    public void beforeExecute(Rule rule, Facts facts) {
        System.out.println("Before executing rule: " + rule.getName());
    }

    @Override
    public void onSuccess(Rule rule, Facts facts) {
        System.out.println("Rule executed successfully: " + rule.getName());
    }

    @Override
    public void onFailure(Rule rule, Facts facts, Exception exception) {
        System.err.println("Rule execution failed: " + rule.getName());
        exception.printStackTrace();
    }
}
