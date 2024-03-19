package com.iokays.extend.image;


import com.iokays.extend.core.MemoryUtility;
import com.sun.management.OperatingSystemMXBean;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.nio.file.Files;
import java.nio.file.Path;

public class ThumbnailatorTester {

    public static void main(String[] args) throws IOException, InterruptedException {
        MemoryUtility.getFreeMemoryPercentage();
        final var bytes = Files.readAllBytes(Path.of("/蒙娜丽莎.jpg"));
        System.out.println(bytes.length);
        getFreeMemoryPercentage();
        compressPic(bytes);
        getFreeMemoryPercentage();
    }

    private static byte[] compressPic(byte[] data) {
        int i = 0;
        Integer fileSize = 4;
        if (fileSize != null) {
            //循环压缩图片到1M 以下
            //大于4M
            if (data.length > Integer.valueOf(fileSize) * 1024 * 1024) {
                do {
                    data = compressPicForScale(data, "0.8");
                    System.out.println(i++);
                } while (data.length > Integer.valueOf(fileSize) * 1024 * 1024);
            }
        } else {
            //压缩图片
            //大于1M
            if (data.length > 1 * 1024 * 1024) {
                do {
                    data = compressPicForScale(data, "0.8");
                    System.out.println(i++);
                } while (data.length > 1 * 1024 * 1024);
            }
        }
        return data;
    }

    private static byte[] compressPicForScale(byte[] data, String byGroupAndName) {
        ByteArrayInputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;
        try {
            inputStream = new ByteArrayInputStream(data);
            outputStream = new ByteArrayOutputStream(data.length);
            Thumbnails.of(inputStream).scale(byGroupAndName == null ? 0.5 : StringUtils.isBlank(byGroupAndName) ? 0.5 : Double.valueOf(byGroupAndName)).toOutputStream(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (null != inputStream) {
                    inputStream.close();
                }
                if (null != outputStream) {
                    outputStream.close();
                }
            } catch (IOException e) {
            }
        }
        return outputStream.toByteArray();
    }


    private static void getFreeMemoryPercentage() {
        System.out.println("================start=====================");
        // 虚拟机级内存情况查询
        long vmFree = 0;
        long vmUse = 0;
        long vmTotal = 0;
        long vmMax = 0;
        int byteToMb = 1024 * 1024;
        Runtime rt = Runtime.getRuntime();
        vmTotal = rt.totalMemory() / byteToMb;
        vmFree = rt.freeMemory() / byteToMb;
        vmMax = rt.maxMemory() / byteToMb;
        vmUse = vmTotal - vmFree;
        System.out.println("JVM内存已用的空间为：" + vmUse + " MB");
        System.out.println("JVM内存的空闲空间为：" + vmFree + " MB");
        System.out.println("JVM总内存空间为：" + vmTotal + " MB");
        System.out.println("JVM总内存空间为：" + vmMax + " MB");

        System.out.println("======================================");
        // 操作系统级内存情况查询
        OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        String os = System.getProperty("os.name");
        long physicalFree = osmxb.getFreePhysicalMemorySize() / byteToMb;
        long physicalTotal = osmxb.getTotalPhysicalMemorySize() / byteToMb;
        long physicalUse = physicalTotal - physicalFree;
        System.out.println("操作系统的版本：" + os);
        System.out.println("操作系统物理内存已用的空间为：" + physicalFree + " MB");
        System.out.println("操作系统物理内存的空闲空间为：" + physicalUse + " MB");
        System.out.println("操作系统总物理内存：" + physicalTotal + " MB");

        // 获得线程总数
        ThreadGroup parentThread;
        int totalThread = 0;
        for (parentThread = Thread.currentThread().getThreadGroup(); parentThread
                .getParent() != null; parentThread = parentThread.getParent()) {
            totalThread = parentThread.activeCount();
        }
        System.out.println("获得线程总数:" + totalThread);
        System.out.println("================end=====================");

    }

}
