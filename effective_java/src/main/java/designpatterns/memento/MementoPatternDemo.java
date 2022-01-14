package designpatterns.memento;

public class MementoPatternDemo {

    public static void main(String[] args) {
        Originator originator = new Originator();
        CareTaker careTaker = new CareTaker();
        originator.setState("state #1");
        originator.setState("state #2");
        careTaker.add(originator.saveStateToMemento());
        originator.setState("state #3");
        careTaker.add(originator.saveStateToMemento());
        originator.setState("state #4");

        System.out.printf("Current State: %s\n", originator.getState());
        originator.getStateFromMemento(careTaker.get(0));
        System.out.printf("First saved State: %s\n", originator.getState());
        originator.getStateFromMemento(careTaker.get(1));
        System.out.printf("Second saved State: %s\n", originator.getState());

    }

}
