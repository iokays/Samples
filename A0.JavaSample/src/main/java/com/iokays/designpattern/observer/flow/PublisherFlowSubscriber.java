package com.iokays.designpattern.observer.flow;

import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName PublisherFlowSubscriber
 * @projectName: object1
 * @author: Zhangmingda
 * @description： XXX
 * date: 2021/4/28.
 */
public class PublisherFlowSubscriber {
    /**
     * 定义用来保持线程不退出的锁
     */
    private static Lock lock = new ReentrantLock(true);
    private static Condition condition = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        /**
         * 定义一个发布者,需要设定要发送消息的泛型数据类型
         */
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
        /**
         * 定义一个订阅者
         */
        MySubscirber<String> subscirber = new MySubscirber<>("订阅者1");
        MySubscirber<String> subscirber2 = new MySubscirber<>("订阅者2");
        /**
         * 通过发布者配置订阅者 会触发订阅者的onSubscribe方法，他们之间的链接纽带会通过参数传递给onSubscribe方法，如果注册失败会触发onError方法
         */
        publisher.subscribe(subscirber);
        publisher.subscribe(subscirber2);

        /**
         * 测试发布消息
         */
        List<String> list =  List.of("张三", "李四", "王五", "赵六");
        list.forEach(publisher::submit); //向订阅者发布数据，需要保持前台的线程存活，否则当前线程执行结束，发布者和订阅者都被销毁了。
        /**
         * 关闭消息发布
         */
        publisher.close(); //关闭后，如果当前线程未退出，待订阅者所有消息都处理完毕才会运行订阅者的onComplete方法
        lock.lock();
        //抛出锁
        condition.await();
        lock.unlock();

    }

    /**
     * 定义订阅者类，需要注意实现接口Flow.Subscriber 实现其泛型传递
     */
    private static class MySubscirber<T> implements Flow.Subscriber<T>{
        /**
         * 订阅者自定义的属性，名字，关联的订阅平台
         */
        private String name;
        private Flow.Subscription subscription;

        public MySubscirber(String name) {
            this.name = name;
        }

        /**
         * 订阅的时候触发的方法
         * @param subscription 订阅者被关联的订阅平台
         */
        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            System.out.println(name + "开启订阅" + subscription);
            /**
             * 从订阅平台获取一条消息
             */
            subscription.request(1);
            /**
             * 将平台实例保存，便于复用
             */
            this.subscription = subscription;
        }

        /**
         * 获取一条数据后触发的方法
         * @param
         */
        @Override
        public void onNext(T t) {
            System.out.println(name + "获取到了一条数据：" +t);
            //再次获取一条数据...自循环触发自己循环调用，一直将所有数据获取完毕
            subscription.request(1);
            /**
             * 模拟处理耗时
             */
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /**
         * 订阅出错时运行的方法
         * @param throwable 错误对象
         */
        @Override
        public void onError(Throwable throwable) {
            throwable.printStackTrace();
        }

        /**
         * 发布者停止发布，且订阅者处理完接收数据后，触发该方法
         */
        @Override
        public void onComplete() {
            System.out.println(name + "发布者关闭了发布");
            lock.lock();
            condition.signalAll();
            lock.unlock();
        }
    }
}