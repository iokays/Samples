package com.iokays.onjava.core;

import com.sun.management.OperatingSystemMXBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.ManagementFactory;
import java.util.function.LongUnaryOperator;

public class MemoryUtility {

    private MemoryUtility() {
        throw new AssertionError("No com.iokays.onjava.core.MemoryUtility instances for you!");
    }

    private static final Logger logger = LoggerFactory.getLogger(MemoryUtility.class);

    public static void getFreeMemoryPercentage() {

        final LongUnaryOperator mb = bytes -> bytes / 1024 / 1024;

        logger.info("虚拟机级内存情况 已使用: {}MB, 空闲: {}MB, 总内存: {}MB, 最大内存: {}MB",
                mb.applyAsLong(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()),
                mb.applyAsLong(Runtime.getRuntime().freeMemory()),
                mb.applyAsLong(Runtime.getRuntime().totalMemory()),
                mb.applyAsLong(Runtime.getRuntime().maxMemory()));


        final OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

        logger.info("操作系统级内存情况 操作系统版本：{}, 已使用: {}MB, 空闲: {}MB, 总内存: {}MB",
                System.getProperty("os.name"),
                mb.applyAsLong(osmxb.getTotalMemorySize() - osmxb.getFreeMemorySize()),
                mb.applyAsLong(osmxb.getFreeMemorySize()),
                mb.applyAsLong(osmxb.getTotalMemorySize()));

        logger.info("获得线程总数: {}", Thread.activeCount());

    }





}
