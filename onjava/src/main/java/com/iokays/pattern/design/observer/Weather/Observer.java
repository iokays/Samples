package com.iokays.pattern.design.observer.Weather;

public interface Observer {
	void update(float temp, float humidity, float pressure);
}
