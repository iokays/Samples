package com.iokays.sample.config;

import java.lang.reflect.Method;

/**
 * Distributed Lock Expression Root Object
 *
 * @param args
 */
record DistributedLockExpressionRootObject(Method method, Object[] args) {
}
