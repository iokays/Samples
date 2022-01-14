package designpatterns.mediator;

public class ConcreteColleague2 extends Colleague {

    @Override
    public void receive() {
        System.out.println("同事2收到请求");
    }

    @Override
    public void send() {
        System.out.println("同事2发出请求");
        mediator.replay(this);
    }
}
