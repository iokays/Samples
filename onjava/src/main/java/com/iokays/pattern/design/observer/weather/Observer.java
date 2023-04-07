package com.iokays.pattern.design.observer.weather;

public interface Observer {
	void update(float temp, float humidity, float pressure);
}
