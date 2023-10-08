package com.iokays.onjava.executors;

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


}
