package com.iokays.pattern.design.observer.flow;


import java.util.List;
import java.util.concurrent.SubmissionPublisher;

public class WeatherStation {

	public static void main(String[] args) throws InterruptedException {
		/**
		 * 定义一个发布者,需要设定要发送消息的泛型数据类型
		 */
		final var publisher = new SubmissionPublisher<WeatherData>();
		/**
		 * 通过发布者配置订阅者 会触发订阅者的onSubscribe方法，他们之间的链接纽带会通过参数传递给onSubscribe方法，如果注册失败会触发onError方法
		 */
		publisher.subscribe(new CurrentConditionsDisplay());
		publisher.subscribe(new ForecastDisplay());
		publisher.subscribe(new StatisticsDisplay());

		List.of(
				new WeatherData(80, 65, 30.4f),
				new WeatherData(82, 70, 29.2f),
				new WeatherData(78, 90, 29.2f)
		).forEach(v -> publisher.submit(v)); //向订阅者发布数据，需要保持前台的线程存活，否则当前线程执行结束，发布者和订阅者都被销毁了。

		/**
		 * 关闭消息发布
		 */
		publisher.close(); //关闭后，如果当前线程未退出，待订阅者所有消息都处理完毕才会运行订阅者的onComplete方法


		Thread.sleep(1000l);

	}
}
