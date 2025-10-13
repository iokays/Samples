package com.iokays.ulid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 演示ULID作为数据库主键的排序优势
 */
public class DatabaseTest {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("### 演示ULID作为数据库主键的排序优势 ###");

        // 模拟一个数据库订单表
        List<Order> orderDatabase = new ArrayList<>();

        // 模拟乱序插入5个订单
        System.out.println("--- 正在乱序创建并插入订单... ---");
        orderDatabase.add(new Order("笔记本电脑"));
        Thread.sleep(5); // 确保时间戳不同
        orderDatabase.add(new Order("机械键盘"));
        Thread.sleep(5);
        orderDatabase.add(new Order("显示器"));
        Thread.sleep(5);
        orderDatabase.add(new Order("鼠标"));
        Thread.sleep(5);
        orderDatabase.add(new Order("耳机"));

        // 打乱顺序，模拟数据库中物理存储的无序性
        Collections.shuffle(orderDatabase);

        System.out.println("\n--- 数据库中当前（物理乱序）的订单 --- ");
        orderDatabase.forEach(System.out::println);

        // 现在，我们利用ULID的特性进行排序
        // 在实际数据库中，这相当于执行 `SELECT * FROM orders ORDER BY id;`
        // 由于ULID本身是按时间排序的，所以按ID排序就等于按创建时间排序
        orderDatabase.sort(null);

        System.out.println("\n--- 按ULID主键排序后的订单（等同于按时间排序） --- ");
        orderDatabase.forEach(System.out::println);

        System.out.println("\n测试结论：使用ULID作为主键，数据库可以直接利用其索引进行高效的时间排序查询，避免了对时间戳字段的额外索引，简化了设计。");
    }
}
