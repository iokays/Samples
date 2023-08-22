package com.iokays.boot.distributedlock;

public interface LockService {
    String lock();
    void failLock();
    String properLock();
}