package designpatterns.command;

public class Light {

    private String name;

    public Light() {
        this.name = "";
    }

    public Light(String name) {
        this.name = name;
    }

    public void on() {
        System.out.println(this.name + " light:on");
    }

    public void off() {
        System.out.println(this.name + " light:off");
    }

}
