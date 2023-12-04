package com.iokays.designpattern.observer.weather;


/**
 * 预测布告板
 */
public class ForecastDisplay implements Observer, DisplayElement {

    /**
     * 气象数据
     */
    private final Subject weatherData;

    /**
     * 当前气压
     */
    private float currentPressure = 29.92f;

    /**
     * 上次气压
     */
    private float lastPressure;

    public ForecastDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        this.weatherData.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.print("Forecast: ");
        if (currentPressure > lastPressure) {
            System.out.println("Improving weather on the way!");
        } else if (currentPressure == lastPressure) {
            System.out.println("More of the same");
        } else if (currentPressure < lastPressure) {
            System.out.println("Watch out for cooler, rainy weather");
        }
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        lastPressure = currentPressure;
        currentPressure = pressure;
        display();
    }
}
