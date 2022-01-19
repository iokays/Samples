package com.iokays.designpatterns.expression;

public class ExpressionTest {

    public static void main(String[] args) {

        Expression robert = new TerminalExpression("Robert");
        Expression john = new TerminalExpression( "John");

        Expression male = new OrExpression(robert, john);

        Expression julie = new TerminalExpression("Julie");
        Expression married = new TerminalExpression("Married");

        Expression marriedWoman = new AndExpression(julie, married);

        System.out.printf("John is male ? %s\n", male.interpret("John"));
        System.out.printf("Julie is a married women ? %s\n", marriedWoman.interpret("Married Julie"));

    }

}
