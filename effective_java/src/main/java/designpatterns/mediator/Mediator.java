package designpatterns.mediator;

public abstract class Mediator {

    public abstract void register(Colleague colleague);

    public abstract void replay(Colleague colleague);

}
