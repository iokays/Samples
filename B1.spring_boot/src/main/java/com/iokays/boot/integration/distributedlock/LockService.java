package com.iokays.boot.integration.distributedlock;

public interface LockService {
    String lock();
    void failLock();
    String properLock();
}