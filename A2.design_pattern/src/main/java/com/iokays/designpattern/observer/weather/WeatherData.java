package com.iokays.designpattern.observer.weather;

import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Objects;

@ApiModel(value = "气象数据")
public class WeatherData implements Subject {

    @ApiModelProperty(value = "观察者")
    private final List<Observer> observers;

    @ApiModelProperty(value = "温度")
    private float temperature;

    @ApiModelProperty(value = "湿度")
    private float humidity;

    @ApiModelProperty(value = "气压")
    private float pressure;

    public WeatherData() {
        this.observers = Lists.newArrayList();
    }

    @Override
    public void registerObserver(Observer observer) {
        Objects.nonNull(observer);
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(observer -> observer.update(temperature, humidity, pressure));
    }

    @ApiOperation(value = "当从气象站得到更新观测值时， 我们通知观察者")
    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure= pressure;
        this.notifyObservers();
    }

}
