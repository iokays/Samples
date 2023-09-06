package com.iokays.boot.mysql.integration.distributedlock;

public interface LockService {
    String lock();
    void failLock();
    String properLock();
}