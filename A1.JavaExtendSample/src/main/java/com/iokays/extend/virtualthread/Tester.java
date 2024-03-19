package com.iokays.extend.virtualthread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;

/**
 *
 */
public class Tester {

    public static void main(String[] args) throws InterruptedException {
        final var executorService = Executors.newVirtualThreadPerTaskExecutor();
        executorService.submit(() -> System.out.println("Hello World!"));
        Thread.sleep(1000);

    }

    @Test
    public void testVirtualThread() throws InterruptedException {
        Thread.startVirtualThread(() -> System.out.println("Hello world"));
        Thread.ofVirtual().name("virtual-thread").start(() -> System.out.println("Hello world"));
        Thread.sleep(1000L);


    }


}
