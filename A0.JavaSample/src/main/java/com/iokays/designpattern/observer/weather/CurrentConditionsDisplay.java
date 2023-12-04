package com.iokays.designpattern.observer.weather;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "当前状况布告板")
public class CurrentConditionsDisplay implements Observer, DisplayElement {

    @ApiModelProperty(value = "气象数据")
    private final Subject weatherData;

    @ApiModelProperty(value = "温度")
    private float temperature;

    @ApiModelProperty(value = "湿度")
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
