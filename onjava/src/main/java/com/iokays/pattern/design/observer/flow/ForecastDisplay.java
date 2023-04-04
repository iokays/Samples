package com.iokays.pattern.design.observer.flow;

import java.util.concurrent.Flow;

public class ForecastDisplay implements Flow.Subscriber<WeatherData>, Observer, DisplayElement {
	private float currentPressure = 29.92f;  
	private float lastPressure;

	private Flow.Subscription subscription;

	public void update(float temp, float humidity, float pressure) {
        lastPressure = currentPressure;
		currentPressure = pressure;

		display();
	}

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
	public void onSubscribe(Flow.Subscription subscription) {
		subscription.request(1);
		this.subscription = subscription;

	}

	@Override
	public void onNext(WeatherData t) {
		this.update(t.temperature(), t.humidity(), t.pressure());

		//再次获取一条数据...自循环触发自己循环调用，一直将所有数据获取完毕
		subscription.request(1);
	}

	@Override
	public void onError(Throwable throwable) {
		throwable.fillInStackTrace();
	}

	@Override
	public void onComplete() {

	}
}
