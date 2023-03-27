package com.iokays.pattern.design.observer.headfirst;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 具体的观察者可以是实现此接口的任意类， 观察者必须注册具体的主题， 以便接收更新。
 */
public class ConcreteObserver implements Observer {

    private static final Logger logger = LoggerFactory.getLogger(ConcreteObserver.class);

    private ConcreteSubject subject;

    public ConcreteObserver(ConcreteSubject subject) {
        this.subject = subject;
        this.subject.registerObserver(this);
    }

    @Override
    public void update() {
        logger.info("invoke.update");
    }
}
