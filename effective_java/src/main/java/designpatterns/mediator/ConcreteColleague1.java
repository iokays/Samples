package designpatterns.mediator;

public class ConcreteColleague1 extends Colleague {

    @Override
    public void receive() {
        System.out.println("同事1收到请求");
    }

    @Override
    public void send() {
        System.out.println("同事1发出请求");
        mediator.replay(this);
    }
}
