package designpatterns.templatemethod;

public class Tea extends CoffeeBeverage {

    public void brew() {
        System.out.println("Steeping the tea");
    }

    public void addCondiments() {
        System.out.println("Adding Lemon");
    }

}
