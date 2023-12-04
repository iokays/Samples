package com.iokays.designpattern.observer.weather;


/**
 * 当前状况布告板
 */
public class CurrentConditionsDisplay implements Observer, DisplayElement {

    /**
     * 气象数据
     */
    private final Subject weatherData;

    /**
     * 温度
     */
    private float temperature;

    /**
     * 湿度
     */
    private float humidity;

    public CurrentConditionsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        this.weatherData.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println("Current conditions: " + temperature + "F degrees and " + humidity + "% humidity");
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        this.temperature = temp;
        this.humidity = humidity;
        this.display();
    }


}
