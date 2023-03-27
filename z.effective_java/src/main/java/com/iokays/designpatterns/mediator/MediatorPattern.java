package com.iokays.designpatterns.mediator;

public class MediatorPattern {

    public static void main(String[] args) {
        Mediator mediator = new ConcreteMediator();
        var c1 = new ConcreteColleague1();
        var c2 = new ConcreteColleague2();

        mediator.register(c1);
        mediator.register(c2);

        c1.send();
        System.out.println("---------------------");
        c2.send();
    }

}
