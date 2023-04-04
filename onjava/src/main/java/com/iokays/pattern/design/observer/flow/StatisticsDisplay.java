package com.iokays.pattern.design.observer.flow;

import java.util.concurrent.Flow;

public class StatisticsDisplay implements Flow.Subscriber<WeatherData>, Observer, DisplayElement {

	private float maxTemp = 0.0f;
	private float minTemp = 200;
	private float tempSum= 0.0f;
	private int numReadings;

	private Flow.Subscription subscription;

	public void update(float temp, float humidity, float pressure) {
		tempSum += temp;
		numReadings++;

		if (temp > maxTemp) {
			maxTemp = temp;
		}

		if (temp < minTemp) {
			minTemp = temp;
		}

		display();
	}

	public void display() {
		System.out.println("Avg/Max/Min temperature = " + (tempSum / numReadings)
				+ "/" + maxTemp + "/" + minTemp);
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
