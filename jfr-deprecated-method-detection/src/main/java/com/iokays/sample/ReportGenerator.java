package com.iokays.sample;

import jdk.jfr.consumer.RecordedEvent;
import jdk.jfr.consumer.RecordingFile;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 步骤2: 报告生成和分析器
 *
 * 这个程序执行两个任务:
 * 1. 分析由 ApiCaller 生成的 JFR 文件，并报告发现的废弃API调用。
 * 2. 演示如何使用动态代理来主动检测项目内部代码的废弃方法调用。
 */
public class ReportGenerator {

    public static void main(String[] args) throws Exception {
        System.out.println("--- 报告与分析程序启动 ---");

        // Part 1: 分析JFR文件
        analyzeJfrFile();

        // Part 2: 演示动态代理
        useDynamicProxyForDeprecationDetection();

        System.out.println("--- 报告与分析程序结束 ---");
    }

    /**
     * 读取JFR文件并分析其中的 jdk.DeprecatedInvocation 事件。
     */
    private static void analyzeJfrFile() throws Exception {
        System.out.println("\n--- [任务1] 分析JFR文件 ---");
        Path jfrFile = Paths.get("deprecated-calls.jfr");

        if (!jfrFile.toFile().exists()) {
            System.out.println("[跳过] JFR文件 'deprecated-calls.jfr' 不存在。");
            System.out.println("请先运行 ApiCaller 并附带JVM参数: -XX:StartFlightRecording:jdk.DeprecatedInvocation#level=all,filename=deprecated-calls.jfr");
            return;
        }

        System.out.println("[执行中] 读取 'deprecated-calls.jfr' 文件...");
        AtomicInteger eventCount = new AtomicInteger(0);
        try (RecordingFile recordingFile = new RecordingFile(jfrFile)) {
            while (recordingFile.hasMoreEvents()) {
                RecordedEvent event = recordingFile.readEvent();
                if ("jdk.DeprecatedInvocation".equals(event.getEventType().getName())) {
                    eventCount.incrementAndGet();
                    String deprecatedMethod = event.getValue("method").toString();
                    boolean forRemoval = event.getValue("forRemoval");
                    System.out.printf("  [JFR发现] 检测到JDK废弃方法调用: %s (forRemoval=%b)\n", deprecatedMethod, forRemoval);
                }
            }
        }
        System.out.printf("[完成] JFR文件分析完毕，共找到 %d 个相关事件。\n", eventCount.get());
    }

    /**
     * 演示使用动态代理来拦截对自定义服务中废弃方法的调用。
     */
    private static void useDynamicProxyForDeprecationDetection() {
        System.out.println("\n--- [任务2] 演示动态代理主动检测 ---");

        MyService service = new MyServiceImpl();
        MyService proxyService = DeprecationDetectorProxy.createProxy(service);

        System.out.println("[执行中] 调用服务方法 (正常与废弃)...");
        proxyService.doSomething();
        proxyService.doSomethingDeprecated();

        System.out.println("[完成] 动态代理检测演示完毕。");
    }

    // --- 动态代理所需的相关接口和类 ---

    public interface MyService {
        void doSomething();

        @Deprecated(since = "1.1", forRemoval = true)
        void doSomethingDeprecated();
    }

    public static class MyServiceImpl implements MyService {
        @Override
        public void doSomething() {
            System.out.println("  (服务正常执行: doSomething)");
        }

        @Override
        public void doSomethingDeprecated() {
            System.out.println("  (服务正常执行: doSomethingDeprecated)");
        }
    }

    public static class DeprecationDetectorProxy implements InvocationHandler {
        private final Object target;

        private DeprecationDetectorProxy(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.isAnnotationPresent(Deprecated.class)) {
                System.out.printf("  [代理警告] 主动检测到废弃方法调用: %s()\n", method.getName());
            }
            return method.invoke(target, args);
        }

        @SuppressWarnings("unchecked")
        public static <T> T createProxy(T target) {
            return (T) Proxy.newProxyInstance(
                    target.getClass().getClassLoader(),
                    target.getClass().getInterfaces(),
                    new DeprecationDetectorProxy(target)
            );
        }
    }
}
