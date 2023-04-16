package com.iokays.pattern.design.observer.weather;

public class WeatherStation {

	public static void main(String[] args) {
		final var weatherData = new WeatherData();
	
		var currentDisplay = new CurrentConditionsDisplay(weatherData);
		var statisticsDisplay = new StatisticsDisplay(weatherData);
		var forecastDisplay = new ForecastDisplay(weatherData);

		weatherData.setMeasurements(80, 65, 30.4f);
		
		System.out.println("---");
		weatherData.setMeasurements(82, 70, 29.2f);
		
		System.out.println("---");
		weatherData.setMeasurements(78, 90, 29.2f);
		
		System.out.println("---Remove Observer---");
		weatherData.removeObserver(forecastDisplay);
		weatherData.setMeasurements(62, 90, 28.1f);
	}
}
