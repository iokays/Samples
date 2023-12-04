package com.iokays.designpattern.observer.weather;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "气象站")
public class WeatherStation {

    public static void main(String[] args) {
        final WeatherData weatherData = new WeatherData();
        final CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay(weatherData);
        final StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
        final ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);
        final HeatIndexDisplay heatIndexDisplay = new HeatIndexDisplay(weatherData);
        weatherData.setMeasurements(80, 65, 30.4f);
        System.out.println("--------------------------------------------------");
        weatherData.setMeasurements(82, 70, 29.2f);
        System.out.println("--------------------------------------------------");
        weatherData.setMeasurements(78, 90, 29.2f);
    }
}
