package com.iokays.onjava.core;

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;
import java.util.function.LongUnaryOperator;

import static io.vavr.API.printf;

public class MemoryUtility {

    private MemoryUtility() {
        throw new AssertionError("No com.iokays.onjava.core.MemoryUtility instances for you!");
    }


    public static void getFreeMemoryPercentage() {

        final LongUnaryOperator mb = bytes -> bytes / 1024 / 1024;

        printf("虚拟机级内存情况 已使用: {0}MB, 空闲: {1}MB, 总内存: {2}MB, 最大内存: {3}MB\n",
                mb.applyAsLong(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()),
                mb.applyAsLong(Runtime.getRuntime().freeMemory()),
                mb.applyAsLong(Runtime.getRuntime().totalMemory()),
                mb.applyAsLong(Runtime.getRuntime().maxMemory()));


        final OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

        printf("操作系统级内存情况 操作系统版本：{0}, 已使用: {1}MB, 空闲: {2}MB, 总内存: {3}MB\n",
                System.getProperty("os.name"),
                mb.applyAsLong(osmxb.getTotalMemorySize() - osmxb.getFreeMemorySize()),
                mb.applyAsLong(osmxb.getFreeMemorySize()),
                mb.applyAsLong(osmxb.getTotalMemorySize()));

        printf("获得线程总数: {0}\n", Thread.activeCount());

    }


}
