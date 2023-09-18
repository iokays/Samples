package com.iokays.onjava.lock;

import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.ReentrantLock;

public class Tester {

    @Test
    public void testLock() {
        var lock = new ReentrantLock();
        lock.lock();
        try {
            System.out.println("lock");
        } finally {
            lock.unlock();
        }

    }
}
