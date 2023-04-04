package com.iokays.pattern.design.observer.flow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Flow;

public class CurrentConditionsDisplay implements Flow.Subscriber<WeatherData>, Observer, DisplayElement {

	private static final Logger logger = LoggerFactory.getLogger(CurrentConditionsDisplay.class);

	private float temperature;
	private float humidity;
	private Flow.Subscription subscription;

	@Override
	public void onSubscribe(Flow.Subscription subscription) {
		/**
		 * 从订阅平台获取一条消息
		 */
		subscription.request(1);
		/**
		 * 将平台实例保存，便于复用
		 */
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

	@Override
	public void update(float temp, float humidity, float pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		display();
	}

	public void display() {
		System.out.println("Current conditions: " + temperature 
			+ "F degrees and " + humidity + "% humidity");
	}

}
